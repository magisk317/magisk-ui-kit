package io.github.magisk317.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

enum class UiKitStyle(val value: Int) {
    Expressive(0),
    Miuix(1),
    ;

    companion object {
        fun fromValue(value: Int): UiKitStyle {
            return entries.firstOrNull { it.value == value } ?: Expressive
        }
    }
}

internal val LocalUiKitStyle = staticCompositionLocalOf { UiKitStyle.Expressive }

@Composable
@ReadOnlyComposable
fun currentUiKitStyle(): UiKitStyle = LocalUiKitStyle.current
