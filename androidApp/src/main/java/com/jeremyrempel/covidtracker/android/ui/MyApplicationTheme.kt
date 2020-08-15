package com.jeremyrempel.covidtracker.android.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun MyAppTheme(enableDarkMode: Boolean, children: @Composable() () -> Unit) {
    // lightColors is a default implementation of the ColorPalette from the MaterialDesign
    // specification https://material.io/design/color/the-color-system.html#color-theme-creation.
    // for easy use. In this case, I'm just showing an example of how you can
    // override any of the values that are a part of the Palette even though I'm just using the
    // default values itself.
    val lightColors = lightColors(
        primary = Color(0xFFee0202),
        primaryVariant = Color(0xFFff5735),
        secondary = Color(0xFFb2000),
        secondaryVariant = Color(0xFFffffff)
    )

    // darkColors is a default implementation of dark mode ColorPalette from the
    // Material Design specification
    // https://material.io/design/color/the-color-system.html#color-theme-creation.
    val darkColors = darkColors(
        primary = Color(0xFFee0202),
        primaryVariant = Color(0xFFff5735),
        secondary = Color(0xFFb2000),
    )
    val colors = if (enableDarkMode) darkColors else lightColors

    // A MaterialTheme comprises of colors, typography and the child composables that are going
    // to make use of this styling.
    MaterialTheme(colors = colors, content = children)
}