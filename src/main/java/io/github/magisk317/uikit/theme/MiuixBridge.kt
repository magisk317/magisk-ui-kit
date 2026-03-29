package io.github.magisk317.uikit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.theme.Colors
import top.yukonga.miuix.kmp.theme.darkColorScheme as miuixDarkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme as miuixLightColorScheme

internal fun ColorScheme.toMiuixColors(
    pureBlack: Boolean = false,
): Colors {
    val isDark = pureBlack || background.red + background.green + background.blue < 1.5f
    val base = if (isDark) miuixDarkColorScheme() else miuixLightColorScheme()
    val adapted = base.copy(
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        onSurfaceVariantSummary = onSurfaceVariant.copy(alpha = 0.88f),
        onSurfaceVariantActions = onSurfaceVariant.copy(alpha = 0.66f),
        outline = outline,
        dividerLine = outlineVariant,
        windowDimming = Color.Black.copy(alpha = if (isDark) 0.6f else 0.3f),
        sliderBackground = surfaceVariant.copy(alpha = 0.3f),
    )
    return if (!pureBlack) {
        adapted
    } else {
        adapted.copy(
            background = Color.Black,
            surface = Color.Black,
            surfaceContainer = Color.Black,
            surfaceContainerHigh = Color.Black,
            surfaceContainerHighest = Color.Black,
        )
    }
}
