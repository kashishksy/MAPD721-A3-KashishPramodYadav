package com.kashish.mapd721_a3_kashishpramodyadav

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestureBasedAnimationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gesture-Based Animation") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Gesture-Based Animations",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // Example 1: Draggable Card
            Text(
                text = "Drag this card:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            DraggableCard()

            Spacer(modifier = Modifier.height(16.dp))

            // Example 2: Rotatable & Scalable Box
            Text(
                text = "Rotate & Scale with two fingers:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            TransformableBox()

            Spacer(modifier = Modifier.height(16.dp))

            // Example 3: Swipe to Dismiss Box
            Text(
                text = "Swipe to transform:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            SwipeToTransformBox()
        }
    }
}

@Composable
fun DraggableCard() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX += delta
                }
            )
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    offsetY += delta
                }
            )
            .size(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Drag Me!",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TransformableBox() {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    val primaryColor = MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation
            )
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, rotationChange ->
                    scale = (scale * zoom).coerceIn(0.5f, 3f)
                    rotation += rotationChange
                }
            }
            .size(150.dp)
            .background(
                color = primaryColor,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Rotate & Scale Me",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun SwipeToTransformBox() {
    val coroutineScope = rememberCoroutineScope()
    val animatedOffset = remember { Animatable(0f) }
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val primaryColor = MaterialTheme.colorScheme.primary
    val errorColor = MaterialTheme.colorScheme.error

    var boxColor by remember { mutableStateOf(tertiaryColor) }
    var boxShape by remember { mutableStateOf(RoundedCornerShape(8.dp)) }

    Box(
        modifier = Modifier
            .offset { IntOffset(animatedOffset.value.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            // When drag ends, animate back to center or change state based on swipe distance
                            if (animatedOffset.value > 300) {
                                // Swiped far right - change to circle
                                boxShape = CircleShape
                                boxColor = primaryColor
                            } else if (animatedOffset.value < -300) {
                                // Swiped far left - change to square
                                boxShape = RoundedCornerShape(0.dp)
                                boxColor = errorColor
                            }
                            // Animate back to center
                            animatedOffset.animateTo(0f, spring(stiffness = Spring.StiffnessLow))
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        // Update the animated value in a coroutine
                        coroutineScope.launch {
                            animatedOffset.snapTo(animatedOffset.value + dragAmount.x)
                        }
                    }
                )
            }
            .size(150.dp)
            .clip(boxShape)
            .background(boxColor)
            .scale(1f - (kotlin.math.abs(animatedOffset.value) / 2000))
            .graphicsLayer {
                // Add some rotation based on offset
                rotationZ = animatedOffset.value / 20
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Swipe Me!",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}