package io.github.magisk317.uikit.surface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.spacing

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
fun MetricGrid(
    metrics: List<MetricSpec>,
    modifier: Modifier = Modifier,
) {
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
    ExpressiveSectionCard(
        title = title,
        summary = summary,
        modifier = modifier,
    ) {
        content()
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
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large),
        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f),
    )
}
