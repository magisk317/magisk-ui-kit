package io.github.magisk317.uikit.theme

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

private const val LIGHT_NAV_SCRIM = 0xE6FFFFFF.toInt()
private const val DARK_NAV_SCRIM = 0x801B1B1B.toInt()

fun applyEdgeToEdge(activity: Activity) {
    WindowCompat.setDecorFitsSystemWindows(activity.window, false)
    applySystemBarStyle(
        window = activity.window,
        darkTheme = activity.isDarkMode(),
    )
}

@Composable
fun UpdateSystemBars(darkTheme: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) {
        return
    }
    val window = (view.context as? Activity)?.window ?: return
    SideEffect {
        applySystemBarStyle(window, darkTheme)
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = !darkTheme
        controller.isAppearanceLightNavigationBars = !darkTheme
    }
}

private fun applySystemBarStyle(window: Window, darkTheme: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        return
    }
    @Suppress("DEPRECATION")
    run {
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = if (darkTheme) DARK_NAV_SCRIM else LIGHT_NAV_SCRIM
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isStatusBarContrastEnforced = false
            window.isNavigationBarContrastEnforced = false
        }
    }
}

private fun Activity.isDarkMode(): Boolean {
    val nightMask = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return nightMask == Configuration.UI_MODE_NIGHT_YES
}
