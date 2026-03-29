package io.github.magisk317.uikit.preference

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.R
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import io.github.magisk317.uikit.theme.spacing
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card as MiuixCard
import top.yukonga.miuix.kmp.basic.Checkbox as MiuixCheckbox
import top.yukonga.miuix.kmp.basic.RadioButton as MiuixRadioButton
import top.yukonga.miuix.kmp.basic.Switch as MiuixSwitch
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun SectionCard(
    title: String,
    accordionMode: Boolean,
    sectionExpanded: Boolean,
    onExpandedChange: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixSectionCard(
            title = title,
            accordionMode = accordionMode,
            sectionExpanded = sectionExpanded,
            onExpandedChange = onExpandedChange,
            content = content,
        )
        return
    }

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
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixItem(
            title = title,
            summary = summary,
            modifier = modifier,
            enabled = enabled,
            trailingContent = trailingContent,
            onClick = onClick,
        )
        return
    }

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
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixStateSwitchItem(
            title = title,
            summary = summary,
            checked = checked,
            enabled = enabled,
            modifier = modifier,
            onTitleClick = onTitleClick,
            onCheckedChange = onCheckedChange,
        )
        return
    }

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
            AppSwitch(
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
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixActionSwitchItem(
            title = title,
            summary = summary,
            checked = checked,
            enabled = enabled,
            modifier = modifier,
            onClick = onClick,
            onCheckedChange = onCheckedChange,
        )
        return
    }

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
            AppSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
        modifier = modifier.clickable(enabled = enabled, onClick = onClick),
    )
}

@Composable
private fun MiuixSectionCard(
    title: String,
    accordionMode: Boolean,
    sectionExpanded: Boolean,
    onExpandedChange: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    MiuixCard(
        modifier = Modifier.fillMaxWidth(),
        insideMargin = PaddingValues(0.dp),
    ) {
        BasicComponent(
            title = title,
            onClick = if (accordionMode) onExpandedChange else null,
            endActions = {
                if (accordionMode) {
                    Image(
                        imageVector = MiuixIcons.Basic.ArrowRight,
                        contentDescription = null,
                        modifier = Modifier
                            .graphicsLayer { rotationZ = if (sectionExpanded) 90f else 0f }
                            .padding(end = 8.dp),
                        colorFilter = ColorFilter.tint(MiuixTheme.colorScheme.onSurfaceVariantActions),
                    )
                }
            },
            insideMargin = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
        )
        AnimatedVisibility(visible = if (accordionMode) sectionExpanded else true) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                content()
            }
        }
    }
}

@Composable
private fun MiuixItem(
    title: String,
    summary: String,
    modifier: Modifier,
    enabled: Boolean,
    trailingContent: @Composable (() -> Unit)?,
    onClick: () -> Unit,
) {
    BasicComponent(
        modifier = modifier.fillMaxWidth(),
        title = title,
        summary = summary.ifEmpty { null },
        enabled = enabled,
        onClick = onClick,
        endActions = {
            if (trailingContent != null) {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically),
                ) {
                    trailingContent()
                }
            }
            MiuixItemArrow(enabled = enabled)
        },
    )
}

@Composable
private fun MiuixStateSwitchItem(
    title: String,
    summary: String,
    checked: Boolean,
    enabled: Boolean,
    modifier: Modifier,
    onTitleClick: (() -> Unit)?,
    onCheckedChange: (Boolean) -> Unit,
) {
    if (onTitleClick == null) {
        SuperSwitch(
            modifier = modifier.fillMaxWidth(),
            checked = checked,
            onCheckedChange = onCheckedChange,
            title = title,
            summary = summary.ifEmpty { null },
            enabled = enabled,
        )
        return
    }

    BasicComponent(
        modifier = modifier.fillMaxWidth(),
        title = title,
        summary = summary.ifEmpty { null },
        enabled = enabled,
        onClick = onTitleClick,
        endActions = {
            MiuixSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
    )
}

@Composable
private fun MiuixActionSwitchItem(
    title: String,
    summary: String,
    checked: Boolean,
    enabled: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    BasicComponent(
        modifier = modifier.fillMaxWidth(),
        title = title,
        summary = summary.ifEmpty { null },
        enabled = enabled,
        onClick = onClick,
        endActions = {
            MiuixSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
    )
}

@Composable
private fun MiuixItemArrow(enabled: Boolean) {
    Image(
        imageVector = MiuixIcons.Basic.ArrowRight,
        contentDescription = null,
        modifier = Modifier.padding(end = 8.dp),
        colorFilter = ColorFilter.tint(
            if (enabled) MiuixTheme.colorScheme.onSurfaceVariantActions
            else MiuixTheme.colorScheme.disabledOnSecondaryVariant,
        ),
    )
}

@Composable
fun AppSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
        )
        return
    }

    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
    )
}

@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixCheckbox(
            state = ToggleableState(checked),
            onClick = if (onCheckedChange != null) {
                { onCheckedChange(!checked) }
            } else {
                null
            },
            modifier = modifier,
            enabled = enabled,
        )
        return
    }

    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
    )
}

@Composable
fun AppRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixRadioButton(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
        )
        return
    }

    RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    )
}
