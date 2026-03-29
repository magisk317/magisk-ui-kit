package io.github.magisk317.uikit.surface

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import io.github.magisk317.uikit.theme.spacing
import top.yukonga.miuix.kmp.basic.SmallTopAppBar

@Composable
fun SearchWorkspaceScaffold(
    fallbackTopPadding: Dp,
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 0.dp,
    overlayModifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    searchField: @Composable ColumnScope.() -> Unit,
    supportingContent: @Composable ColumnScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    OverlayHeaderScaffold(
        modifier = modifier,
        fallbackTopPadding = fallbackTopPadding,
        bottomPadding = bottomPadding,
        overlayModifier = overlayModifier,
        overlay = {
            OverlayHeaderPanel(
                title = title,
                subtitle = subtitle,
                actions = actions,
            ) {
                searchField()
                supportingContent()
            }
        },
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkspaceTopBarSearchOverlay(
    title: String,
    searchQuery: String,
    searchPlaceholder: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    windowInsets: WindowInsets = WindowInsets.statusBars,
    supportingContent: @Composable ColumnScope.() -> Unit = {},
    onSearchChange: (String) -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixWorkspaceTopBarSearchOverlay(
            title = title,
            searchQuery = searchQuery,
            searchPlaceholder = searchPlaceholder,
            modifier = modifier,
            navigationIcon = navigationIcon,
            actions = actions,
            supportingContent = supportingContent,
            onSearchChange = onSearchChange,
        )
        return
    }

    ColumnScopeInstance(modifier) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            navigationIcon = {
                navigationIcon?.invoke()
            },
            actions = actions,
            scrollBehavior = scrollBehavior,
            windowInsets = windowInsets,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
            ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium, vertical = MaterialTheme.spacing.small),
        ) {
            WorkspaceSearchField(
                query = searchQuery,
                placeholder = searchPlaceholder,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onSearchChange,
            )
        }
        supportingContent()
    }
}

@Composable
private fun ColumnScopeInstance(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        content = content,
    )
}

@Composable
private fun MiuixWorkspaceTopBarSearchOverlay(
    title: String,
    searchQuery: String,
    searchPlaceholder: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    supportingContent: @Composable ColumnScope.() -> Unit = {},
    onSearchChange: (String) -> Unit,
) {
    ColumnScopeInstance(modifier) {
        SmallTopAppBar(
            title = title,
            navigationIcon = { navigationIcon?.invoke() },
            actions = actions,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            WorkspaceSearchField(
                query = searchQuery,
                placeholder = searchPlaceholder,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onSearchChange,
            )
        }
        supportingContent()
    }
}
