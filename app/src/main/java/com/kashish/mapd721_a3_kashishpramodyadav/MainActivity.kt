package com.kashish.mapd721_a3_kashishpramodyadav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kashish.mapd721_a3_kashishpramodyadav.ui.theme.MAPD721A3KashishPramodYadavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationDemoTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    AnimationApp()
                }
            }
        }
    }
}

@Composable
fun AnimationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("animated_content") {
            AnimatedContentScreen(navController = navController)
        }
         composable("value_based_transition") {
            ValueBasedTransitionScreen(navController = navController)
        }
        composable("infinite_transition") {
            InfiniteTransitionScreen(navController = navController)
        }
        composable("gesture_based") {
            GestureBasedAnimationScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationAppPreview() {
    AnimationDemoTheme {
        AnimationApp()
    }
}