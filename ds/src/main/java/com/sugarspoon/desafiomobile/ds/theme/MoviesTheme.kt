package com.sugarspoon.desafiomobile.ds.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MoviesColorScheme = darkColorScheme(
    primary = RedPrimary,
    secondary = GraySecondary,
    tertiary = GrayTertiary,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = TextFaded
)

@Composable
fun MoviesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MoviesColorScheme,
        typography = Typography,
        content = content,
    )
}