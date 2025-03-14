package com.kashish.mapd721_a3_kashishpramodyadav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Define your custom colors
private val PrimaryColor = Color(0xFFE64A19) // Deep Orange
private val SecondaryColor = Color(0xFF00BFA5) // Teal Accent
private val TertiaryColor = Color(0xFFFFB300) // Amber
private val BackgroundStart = Color(0xFF2C3E50) // Dark Blue-Gray
private val BackgroundEnd = Color(0xFF1A237E) // Deep Purple
private val SurfaceColor = Color(0xFF37474F) // Blue Gray
private val OnSurfaceColor = Color.White
private val OnPrimaryColor = Color.White
private val OnSecondaryColor = Color.Black

// Create custom color schemes
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = BackgroundStart, // Will be overridden by gradient
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = BackgroundStart, // Will be overridden by gradient
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor
)

// Custom Typography
private val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
)

@Composable
fun AnimationDemoTheme(
    darkTheme: Boolean = true, // Default to dark theme
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography
    ) {
        // Create a gradient background box
        GradientBackground {
            content()
        }
    }
}

@Composable
fun GradientBackground(content: @Composable BoxScope.() -> Unit) {
    // Create a linear gradient background from top to bottom
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            BackgroundStart,
            BackgroundEnd
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush),
        content = content
    )
}