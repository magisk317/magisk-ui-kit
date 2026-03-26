package io.github.magisk317.uikit.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

enum class MagiskThemeMode(val value: Int) {
    System(0),
    Light(1),
    Dark(2),
    PureBlack(3),
    ;

    companion object {
        fun fromValue(value: Int): MagiskThemeMode {
            return entries.firstOrNull { it.value == value } ?: System
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MagiskUiKitTheme(
    themeMode: Int,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val resolvedMode = MagiskThemeMode.fromValue(themeMode)
    val darkTheme = when (resolvedMode) {
        MagiskThemeMode.Light -> false
        MagiskThemeMode.Dark, MagiskThemeMode.PureBlack -> true
        MagiskThemeMode.System -> isSystemInDarkTheme()
    }

    val context = LocalContext.current
    val colorScheme = when {
        resolvedMode == MagiskThemeMode.PureBlack -> {
            val darkBase = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                dynamicDarkColorScheme(context)
            } else {
                darkColorScheme()
            }
            darkBase.copy(
                background = Color.Black,
                surface = Color.Black,
                surfaceContainer = Color.Black,
                surfaceContainerLow = Color.Black,
                surfaceContainerLowest = Color.Black,
                surfaceContainerHigh = Color.Black,
                surfaceContainerHighest = Color.Black,
            )
        }

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    UpdateSystemBars(darkTheme)

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
    ) {
        MaterialExpressiveTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}
