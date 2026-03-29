package io.github.magisk317.uikit.surface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import io.github.magisk317.uikit.theme.spacing
import top.yukonga.miuix.kmp.basic.Button as MiuixButton
import top.yukonga.miuix.kmp.basic.ButtonDefaults as MiuixButtonDefaults
import top.yukonga.miuix.kmp.basic.FloatingActionButton as MiuixFloatingActionButton
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator as MiuixLinearProgressIndicator
import top.yukonga.miuix.kmp.basic.TextField as MiuixTextField
import top.yukonga.miuix.kmp.basic.TextButton as MiuixTextButton

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            colors = MiuixButtonDefaults.buttonColorsPrimary(),
        ) {
            Text(text = text)
        }
        return
    }

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = text)
    }
}

@Composable
fun AppSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
        ) {
            Text(text = text)
        }
        return
    }

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = text)
    }
}

@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixTextButton(
            text = text,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
        )
        return
    }

    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = text)
    }
}

@Composable
fun AppFloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixFloatingActionButton(
            onClick = onClick,
            modifier = modifier,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier.size(22.dp),
            )
        }
        return
    }

    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun AppLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixLinearProgressIndicator(
            progress = progress,
            modifier = modifier,
        )
        return
    }

    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier,
    )
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            MiuixTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                label = label,
                enabled = enabled,
                readOnly = readOnly,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation,
                onTextLayout = onTextLayout,
                useLabelAsPlaceholder = placeholder != null,
            )
            if (supportingText != null) {
                Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                    supportingText()
                }
            }
        }
        return
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        placeholder = placeholder,
        supportingText = supportingText,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
    )
}

@Composable
fun AppTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            MiuixTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                label = label,
                enabled = enabled,
                readOnly = readOnly,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation,
            )
            if (supportingText != null) {
                Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                    supportingText()
                }
            }
        }
        return
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        supportingText = supportingText,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
    )
}

@Composable
fun SectionHeading(
    title: String,
    summary: String? = null,
    modifier: Modifier = Modifier,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                style = top.yukonga.miuix.kmp.theme.MiuixTheme.textStyles.title3,
                color = top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme.onSurface,
            )
            if (!summary.isNullOrBlank()) {
                Text(
                    text = summary,
                    style = top.yukonga.miuix.kmp.theme.MiuixTheme.textStyles.body2,
                    color = top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme.onSurfaceVariantSummary,
                )
            }
        }
        return
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        if (!summary.isNullOrBlank()) {
            Text(
                text = summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
fun DialogButtonRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small, androidx.compose.ui.Alignment.End),
        content = content,
    )
}
