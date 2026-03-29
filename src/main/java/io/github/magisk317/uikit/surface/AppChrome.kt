package io.github.magisk317.uikit.surface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import io.github.magisk317.uikit.theme.spacing
import top.yukonga.miuix.kmp.basic.Card as MiuixCard
import top.yukonga.miuix.kmp.basic.HorizontalDivider as MiuixHorizontalDivider
import top.yukonga.miuix.kmp.theme.MiuixTheme

private const val METRIC_GRID_ITEM_WIDTH_FRACTION = 0.5f

data class MetricSpec(
    val label: String,
    val value: String,
    val accent: Color? = null,
)

@Composable
fun SectionColumn(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(contentPadding),
        verticalArrangement = verticalArrangement,
    ) {
        content()
    }
}

@Composable
fun FeatureEntryCard(
    title: String,
    summary: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixFeatureEntryCard(
            title = title,
            summary = summary,
            modifier = modifier,
            onClick = onClick,
        )
        return
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
fun StatusHeroCard(
    title: String,
    summary: String? = null,
    icon: ImageVector,
    highlighted: Boolean,
    modifier: Modifier = Modifier,
    diagnostics: List<Pair<String, String>> = emptyList(),
    onClick: (() -> Unit)? = null,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixCard(
            modifier = modifier.fillMaxWidth(),
            onClick = onClick,
            colors = top.yukonga.miuix.kmp.basic.CardDefaults.defaultColors(
                color = if (highlighted) {
                    MiuixTheme.colorScheme.primaryContainer
                } else {
                    MiuixTheme.colorScheme.errorContainer
                },
                contentColor = if (highlighted) {
                    MiuixTheme.colorScheme.onPrimaryContainer
                } else {
                    MiuixTheme.colorScheme.onErrorContainer
                },
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    top.yukonga.miuix.kmp.basic.Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 2.dp),
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            text = title,
                            style = MiuixTheme.textStyles.title2,
                            fontWeight = FontWeight.Bold,
                        )
                        if (!summary.isNullOrBlank()) {
                            Text(
                                text = summary,
                                style = MiuixTheme.textStyles.body2,
                                color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                            )
                        }
                    }
                }
                if (diagnostics.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        diagnostics.forEach { (label, value) ->
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                Text(
                                    text = label,
                                    style = MiuixTheme.textStyles.footnote1,
                                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                                )
                                Text(
                                    text = value,
                                    style = MiuixTheme.textStyles.body2,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                    }
                }
            }
        }
        return
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = if (highlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.errorContainer,
            contentColor = if (highlighted) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onErrorContainer,
        ),
        onClick = { onClick?.invoke() },
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                )
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    if (!summary.isNullOrBlank()) {
                        Text(
                            text = summary,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            if (diagnostics.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    diagnostics.forEach { (label, value) ->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                            )
                            Text(
                                text = value,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MetricGrid(
    metrics: List<MetricSpec>,
    modifier: Modifier = Modifier,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 2,
        ) {
            metrics.forEach { metric ->
                Box(modifier = Modifier.fillMaxWidth(METRIC_GRID_ITEM_WIDTH_FRACTION)) {
                    MetricCard(
                        label = metric.label,
                        value = metric.value,
                        modifier = Modifier.fillMaxWidth(),
                        accent = metric.accent ?: MiuixTheme.colorScheme.primary,
                    )
                }
            }
        }
        return
    }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        maxItemsInEachRow = 2,
    ) {
        metrics.forEach { metric ->
            Box(modifier = Modifier.fillMaxWidth(METRIC_GRID_ITEM_WIDTH_FRACTION)) {
                MetricCard(
                    label = metric.label,
                    value = metric.value,
                    modifier = Modifier.fillMaxWidth(),
                    accent = metric.accent ?: MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
fun DetailSectionCard(
    title: String,
    summary: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixDetailSectionCard(
            title = title,
            summary = summary,
            modifier = modifier,
            content = content,
        )
    } else {
        ExpressiveSectionCard(
            title = title,
            summary = summary,
            modifier = modifier,
        ) {
            content()
        }
    }
}

@Composable
fun DetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    leadingContent: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixSummaryRow(
            icon = null,
            label = label,
            value = value,
            modifier = modifier,
            onClick = onClick,
        )
        return
    }

    ListItem(
        leadingContent = leadingContent?.let {
            {
                it()
            }
        },
        headlineContent = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
            )
        },
        supportingContent = null,
        trailingContent = {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        modifier = if (onClick != null) modifier.clickable(onClick = onClick) else modifier,
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
    )
}

@Composable
fun DetailDivider() {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixHorizontalDivider(
            modifier = Modifier.padding(horizontal = 18.dp),
            color = MiuixTheme.colorScheme.dividerLine,
        )
    } else {
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f),
        )
    }
}

@Composable
fun SummarySectionCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixCard(
            modifier = modifier.fillMaxWidth(),
            insideMargin = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                content = content,
            )
        }
    } else {
        ElevatedCard(
            modifier = modifier,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                content = content,
            )
        }
    }
}

@Composable
fun SummaryRow(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixSummaryRow(
            icon = icon,
            label = label,
            value = value,
            modifier = modifier,
            onClick = onClick,
        )
        return
    }

    ListItem(
        leadingContent = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        headlineContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        modifier = if (onClick != null) modifier.clickable(onClick = onClick) else modifier,
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
    )
}

@Composable
private fun MiuixFeatureEntryCard(
    title: String,
    summary: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    MiuixCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MiuixTheme.textStyles.title3,
                color = MiuixTheme.colorScheme.onSurface,
            )
            Text(
                text = summary,
                style = MiuixTheme.textStyles.body2,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
            )
        }
    }
}

@Composable
private fun MiuixDetailSectionCard(
    title: String,
    summary: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    MiuixCard(
        modifier = modifier.fillMaxWidth(),
        insideMargin = PaddingValues(0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MiuixTheme.textStyles.title3,
                color = MiuixTheme.colorScheme.onSurface,
            )
            if (!summary.isNullOrBlank()) {
                Text(
                    text = summary,
                    style = MiuixTheme.textStyles.body2,
                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                )
            }
        }
        MiuixHorizontalDivider(
            modifier = Modifier.padding(horizontal = 18.dp),
            color = MiuixTheme.colorScheme.dividerLine,
        )
        Column(content = content)
    }
}

@Composable
private fun MiuixSummaryRow(
    icon: ImageVector?,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    top.yukonga.miuix.kmp.basic.BasicComponent(
        modifier = modifier.fillMaxWidth(),
        title = label,
        summary = value,
        startAction = if (icon != null) {
            {
                top.yukonga.miuix.kmp.basic.Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MiuixTheme.colorScheme.primary,
                )
            }
        } else {
            null
        },
        onClick = onClick,
    )
}
