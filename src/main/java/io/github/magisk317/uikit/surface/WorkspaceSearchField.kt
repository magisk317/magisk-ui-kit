package io.github.magisk317.uikit.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.github.magisk317.uikit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkspaceSearchField(
    query: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Surface(
        color = Color.Transparent,
        shape = MaterialTheme.shapes.extraLarge,
        tonalElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .background(
                MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f),
                MaterialTheme.shapes.extraLarge,
            ),
    ) {
        TextField(
            value = query,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            placeholder = { Text(placeholder) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search_black_24dp),
                    contentDescription = null,
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clear_black_24dp),
                            contentDescription = null,
                        )
                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
    }
}
