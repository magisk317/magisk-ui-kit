package io.github.magisk317.uikit.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import top.yukonga.miuix.kmp.basic.NavigationBar as MiuixNavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem as MiuixNavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationBarDisplayMode
import top.yukonga.miuix.kmp.basic.NavigationRail as MiuixNavigationRail
import top.yukonga.miuix.kmp.basic.NavigationRailItem as MiuixNavigationRailItem
import top.yukonga.miuix.kmp.basic.NavigationRailDisplayMode
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.theme.MiuixTheme

data class AppNavigationItemSpec(
    val label: String,
    val icon: ImageVector,
    val selected: Boolean,
    val onClick: () -> Unit,
)

@Composable
fun AppBottomNavigationBar(
    items: List<AppNavigationItemSpec>,
    modifier: Modifier = Modifier,
    alwaysShowLabel: Boolean = false,
    containerColor: Color = Color.Transparent,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixNavigationBar(
            modifier = modifier,
            color = if (containerColor == Color.Transparent) {
                MiuixTheme.colorScheme.surface
            } else {
                containerColor
            },
            mode = if (alwaysShowLabel) {
                NavigationBarDisplayMode.IconAndText
            } else {
                NavigationBarDisplayMode.IconWithSelectedLabel
            },
        ) {
            items.forEach { item ->
                MiuixNavigationBarItem(
                    selected = item.selected,
                    onClick = item.onClick,
                    icon = item.icon,
                    label = item.label,
                )
            }
        }
        return
    }

    NavigationBar(
        modifier = modifier.navigationBarsPadding(),
        containerColor = containerColor,
        tonalElevation = 0.dp,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = item.selected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.22f),
                ),
                alwaysShowLabel = alwaysShowLabel,
                onClick = item.onClick,
            )
        }
    }
}

@Composable
fun AppNavigationRail(
    items: List<AppNavigationItemSpec>,
    modifier: Modifier = Modifier,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    alwaysShowLabel: Boolean = false,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixNavigationRail(
            modifier = modifier.fillMaxHeight(),
            header = header,
            mode = if (alwaysShowLabel) {
                NavigationRailDisplayMode.IconAndText
            } else {
                NavigationRailDisplayMode.IconWithSelectedLabel
            },
        ) {
            items.forEach { item ->
                MiuixNavigationRailItem(
                    selected = item.selected,
                    onClick = item.onClick,
                    icon = item.icon,
                    label = item.label,
                )
            }
        }
        return
    }

    NavigationRail(
        header = header,
        modifier = modifier.fillMaxHeight(),
    ) {
        items.forEach { item ->
            NavigationRailItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = item.selected,
                alwaysShowLabel = alwaysShowLabel,
                onClick = item.onClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    windowInsets: WindowInsets = WindowInsets.statusBars,
    containerColor: Color = Color.Transparent,
    scrolledContainerColor: Color = Color.Transparent,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        SmallTopAppBar(
            title = title,
            modifier = modifier,
            navigationIcon = { navigationIcon?.invoke() },
            actions = actions,
            color = if (containerColor == Color.Transparent) {
                MiuixTheme.colorScheme.surface
            } else {
                containerColor
            },
            defaultWindowInsetsPadding = true,
        )
        return
    }

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = { navigationIcon?.invoke() },
        actions = actions,
        scrollBehavior = scrollBehavior,
        windowInsets = windowInsets,
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = scrolledContainerColor,
        ),
    )
}
