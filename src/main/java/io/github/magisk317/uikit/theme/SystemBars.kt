package io.github.magisk317.uikit.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun UpdateSystemBars(darkTheme: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) {
        return
    }
    val window = (view.context as? Activity)?.window ?: return
    SideEffect {
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = !darkTheme
        controller.isAppearanceLightNavigationBars = !darkTheme
    }
}
