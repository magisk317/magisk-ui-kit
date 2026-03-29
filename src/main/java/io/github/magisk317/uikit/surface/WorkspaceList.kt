package io.github.magisk317.uikit.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import io.github.magisk317.uikit.theme.spacing
import top.yukonga.miuix.kmp.basic.Card as MiuixCard
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun WorkspaceListItem(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixCard(
            modifier = modifier
                .fillMaxWidth()
                .then(
                    if (onLongClick != null) {
                        Modifier.combinedClickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onClick ?: {},
                            onLongClick = onLongClick,
                        )
                    } else if (onClick != null) {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onClick,
                        )
                    } else {
                        Modifier
                    }
                ),
            insideMargin = PaddingValues(0.dp),
            colors = top.yukonga.miuix.kmp.basic.CardDefaults.defaultColors(
                color = if (containerColor == Color.Transparent) {
                    MiuixTheme.colorScheme.surfaceContainer
                } else {
                    containerColor
                },
                contentColor = MiuixTheme.colorScheme.onSurfaceContainer,
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (leadingContent != null) {
                    leadingContent()
                    Spacer(modifier = Modifier.size(16.dp))
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    content = content,
                )
                if (trailingContent != null) {
                    Spacer(modifier = Modifier.size(12.dp))
                    trailingContent()
                }
            }
        }
        return
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (onLongClick != null) {
                        Modifier.combinedClickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onClick ?: {},
                            onLongClick = onLongClick,
                        )
                    } else if (onClick != null) {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onClick,
                        )
                    } else {
                        Modifier
                    }
                )
                .padding(
                    start = MaterialTheme.spacing.large,
                    end = MaterialTheme.spacing.extraLarge,
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.medium,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingContent != null) {
                leadingContent()
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                content = content,
            )
            if (trailingContent != null) {
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
                trailingContent()
            }
        }
    }
}

@Composable
fun WorkspaceEmptyState(
    title: String,
    summary: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.size(16.dp))
            }
            Text(
                text = title,
                style = MiuixTheme.textStyles.title2,
                color = MiuixTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = summary,
                style = MiuixTheme.textStyles.body2,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
            )
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (icon != null) {
            icon()
            Spacer(modifier = Modifier.size(16.dp))
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = summary,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun WorkspaceTrailingIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = modifier.size(20.dp),
        tint = tint,
    )
}
