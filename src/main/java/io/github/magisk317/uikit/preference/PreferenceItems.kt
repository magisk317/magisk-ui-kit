package io.github.magisk317.uikit.preference

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.magisk317.uikit.R
import io.github.magisk317.uikit.theme.spacing

@Composable
fun SectionCard(
    title: String,
    accordionMode: Boolean,
    sectionExpanded: Boolean,
    onExpandedChange: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ListItem(
                headlineContent = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                trailingContent = {
                    if (accordionMode) {
                        Icon(
                            painter = painterResource(
                                if (sectionExpanded) {
                                    R.drawable.ic_keyboard_arrow_up_black_24dp
                                } else {
                                    R.drawable.ic_keyboard_arrow_down_black_24dp
                                }
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = accordionMode, onClick = onExpandedChange),
            )

            AnimatedVisibility(visible = if (accordionMode) sectionExpanded else true) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    content()
                }
            }
        }
    }
}

@Composable
fun Item(
    title: String,
    summary: String = "",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        supportingContent = if (summary.isNotEmpty()) {
            {
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            null
        },
        trailingContent = trailingContent,
        modifier = modifier.clickable(enabled = enabled, onClick = onClick),
    )
}

@Composable
fun StateSwitchItem(
    title: String,
    summary: String,
    checked: Boolean,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onTitleClick: (() -> Unit)? = null,
    onCheckedChange: (Boolean) -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = if (onTitleClick != null) {
                    Modifier.clickable(enabled = enabled, onClick = onTitleClick)
                } else {
                    Modifier
                },
            )
        },
        supportingContent = if (summary.isNotEmpty()) {
            {
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = if (onTitleClick != null) {
                        Modifier.clickable(enabled = enabled, onClick = onTitleClick)
                    } else {
                        Modifier
                    },
                )
            }
        } else {
            null
        },
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
        modifier = if (onTitleClick == null) {
            modifier.clickable(enabled = enabled) { onCheckedChange(!checked) }
        } else {
            modifier
        },
    )
}

@Composable
fun ActionSwitchItem(
    title: String,
    summary: String,
    checked: Boolean,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        supportingContent = if (summary.isNotEmpty()) {
            {
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            null
        },
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
        modifier = modifier.clickable(enabled = enabled, onClick = onClick),
    )
}
