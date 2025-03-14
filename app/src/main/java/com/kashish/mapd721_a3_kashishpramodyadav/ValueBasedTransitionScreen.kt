package com.kashish.mapd721_a3_kashishpramodyadav

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ValueBasedTransitionScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }
    var isCircle by remember { mutableStateOf(false) }

    // Create a transition state
    val transition = updateTransition(
        targetState = isVisible,
        label = "Visibility Transition"
    )

    // Create a second transition for shape morphing
    val shapeTransition = updateTransition(
        targetState = isCircle,
        label = "Shape Transition"
    )

    // Define animated values
    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) },
        label = "Alpha"
    ) { state ->
        if (state) 1f else 0.2f
    }

    val size by transition.animateDp(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) },
        label = "Size"
    ) { state ->
        if (state) 200.dp else 100.dp
    }

    val color by transition.animateColor(
        transitionSpec = { tween(durationMillis = 1000) },
        label = "Color"
    ) { state ->
        if (state) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary
    }

    // Shape transition values
    val cornerRadius by shapeTransition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "Corner Radius"
    ) { state ->
        if (state) 100.dp else 16.dp
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Value-Based Animation",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                )
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
                text = "Value-Based Transitions",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )

            // Animated Box using transition values
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(color.copy(alpha = alpha))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Animated\nBox",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { isVisible = !isVisible },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(if (isVisible) "Minimize" else "Maximize")
                }

                Button(
                    onClick = { isCircle = !isCircle },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(if (isCircle) "Make Square" else "Make Circle")
                }
            }
        }
    }
}