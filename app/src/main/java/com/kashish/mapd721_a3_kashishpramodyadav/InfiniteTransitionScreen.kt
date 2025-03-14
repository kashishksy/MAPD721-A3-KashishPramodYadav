package com.kashish.mapd721_a3_kashishpramodyadav

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfiniteTransitionScreen(navController: NavController) {
    // Create an infinite transition
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    // Pulsating animation
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    // Color animation
    val color by infiniteTransition.animateColor(
        initialValue = Color(0xFF6200EE),
        targetValue = Color(0xFF03DAC5),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )

    // Rotation animation
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Orbit animation
    val orbitAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "orbit"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Infinite Transitions") },
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
                text = "Infinite Animation Examples",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // First animation example: Pulsating circle
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Pulsating Animation",
                        modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .scale(scale)
                            .clip(CircleShape)
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Pulse",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Second animation example: Rotating element
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Rotation Animation",
                        modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotation)
                            .background(MaterialTheme.colorScheme.tertiary)
                    )
                }
            }

            // Third animation example: Orbiting elements
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Orbiting Animation",
                        modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Central circle
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    // Orbiting circles
                    for (i in 0 until 3) {
                        val angle = orbitAngle + (i * 120f)  // Evenly space 3 orbiting elements
                        val radius = 60f  // Orbit radius
                        val x = cos(Math.toRadians(angle.toDouble())).toFloat() * radius
                        val y = sin(Math.toRadians(angle.toDouble())).toFloat() * radius

                        Box(
                            modifier = Modifier
                                .offset(x.dp, y.dp)
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(color)
                        )
                    }
                }
            }

            // Fourth animation: Wave pattern using Canvas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Wave Animation",
                        modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    val waveProgress by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart
                        ),
                        label = "wave"
                    )

                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        val centerY = canvasHeight / 2

                        // Draw wave
                        for (x in 0..canvasWidth.toInt() step 5) {
                            val xRatio = x / canvasWidth
                            val y = centerY + sin((xRatio * 8 + waveProgress) * 2 * Math.PI) * 30

                            drawCircle(
                                color = color,
                                radius = 5f,
                                center = Offset(x.toFloat(), y.toFloat())
                            )
                        }
                    }
                }
            }
        }
    }
}