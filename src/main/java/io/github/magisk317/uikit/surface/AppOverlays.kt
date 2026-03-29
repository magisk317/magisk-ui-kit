package io.github.magisk317.uikit.surface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.theme.LocalUiKitStyle
import io.github.magisk317.uikit.theme.UiKitStyle
import io.github.magisk317.uikit.theme.spacing
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.basic.Card as MiuixCard

@Composable
fun AppDialogSurface(
    title: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        MiuixCard(
            modifier = modifier.fillMaxWidth(),
            insideMargin = PaddingValues(0.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                if (!title.isNullOrBlank()) {
                    Text(
                        text = title,
                        style = MiuixTheme.textStyles.title2,
                        color = MiuixTheme.colorScheme.onSurface,
                    )
                }
                content()
            }
        }
        return
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            content()
        }
    }
}

@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    title: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    confirmButton: @Composable (() -> Unit) = {},
    dismissButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                AppDialogSurface(modifier = modifier) {
                    if (title != null) {
                        title()
                    }
                    if (text != null) {
                        text()
                    }
                    DialogButtonRow {
                        dismissButton?.invoke()
                        confirmButton()
                    }
                }
            }
        }
        return
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = title,
        text = text,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        modifier = modifier,
        properties = properties,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBasicDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                content()
            }
        }
        return
    }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    show: Boolean,
    onDismissRequest: () -> Unit,
    title: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (!show) return

    if (LocalUiKitStyle.current == UiKitStyle.Miuix) {
        WindowBottomSheet(
            show = true,
            modifier = modifier,
            title = title,
            onDismissRequest = onDismissRequest,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = content,
            )
        }
        return
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            content = content,
        )
    }
}
