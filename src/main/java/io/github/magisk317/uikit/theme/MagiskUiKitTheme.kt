package io.github.magisk317.uikit.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import top.yukonga.miuix.kmp.theme.MiuixTheme

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
    uiKitStyle: UiKitStyle = UiKitStyle.Expressive,
    content: @Composable () -> Unit,
) {
    val resolvedMode = MagiskThemeMode.fromValue(themeMode)
    val darkTheme = when (resolvedMode) {
        MagiskThemeMode.Light -> false
        MagiskThemeMode.Dark, MagiskThemeMode.PureBlack -> true
        MagiskThemeMode.System -> isSystemInDarkTheme()
    }

    val context = LocalContext.current
    val useDynamicColor = dynamicColor && uiKitStyle != UiKitStyle.Miuix
    val lightScheme = if (useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicLightColorScheme(context)
    } else {
        lightColorScheme()
    }
    val darkScheme = if (useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context)
    } else {
        darkColorScheme()
    }
    val colorScheme: ColorScheme = when {
        resolvedMode == MagiskThemeMode.PureBlack -> {
            darkScheme.copy(
                background = Color.Black,
                surface = Color.Black,
                surfaceContainer = Color.Black,
                surfaceContainerLow = Color.Black,
                surfaceContainerLowest = Color.Black,
                surfaceContainerHigh = Color.Black,
                surfaceContainerHighest = Color.Black,
            )
        }
        darkTheme -> darkScheme
        else -> lightScheme
    }

    UpdateSystemBars(darkTheme)

    CompositionLocalProvider(
        LocalUiKitStyle provides uiKitStyle,
        LocalSpacing provides Spacing(),
    ) {
        MaterialExpressiveTheme(
            colorScheme = colorScheme,
        ) {
            if (uiKitStyle == UiKitStyle.Miuix) {
                val miuixColors = remember(colorScheme, resolvedMode) {
                    colorScheme.toMiuixColors(pureBlack = resolvedMode == MagiskThemeMode.PureBlack)
                }
                MiuixTheme(
                    colors = miuixColors,
                    content = content,
                )
            } else {
                content()
            }
        }
    }
}
