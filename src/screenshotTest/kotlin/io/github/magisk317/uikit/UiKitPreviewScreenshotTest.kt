package io.github.magisk317.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import io.github.magisk317.uikit.preference.SectionCard
import io.github.magisk317.uikit.preference.StateSwitchItem
import io.github.magisk317.uikit.surface.ExpressiveHeroCard
import io.github.magisk317.uikit.surface.WorkspaceSearchField
import io.github.magisk317.uikit.theme.MagiskThemeMode
import io.github.magisk317.uikit.theme.MagiskUiKitTheme

@PreviewTest
@Preview(name = "SectionCardLight", showBackground = true, backgroundColor = 0xFFF4F1EA, widthDp = 380)
@Composable
fun SectionCardLightPreview() {
    PreviewFrame {
        SectionCard(
            title = "Verification",
            accordionMode = false,
            sectionExpanded = true,
            onExpandedChange = {},
        ) {
            StateSwitchItem(
                title = "Show Notification",
                summary = "Display the parsed code in a high-priority banner.",
                checked = true,
                onCheckedChange = {},
            )
        }
    }
}

@PreviewTest
@Preview(name = "HeroCardDark", showBackground = true, backgroundColor = 0xFF121212, widthDp = 380)
@Composable
fun HeroCardDarkPreview() {
    PreviewFrame(themeMode = MagiskThemeMode.Dark.value) {
        ExpressiveHeroCard(
            title = "Relay Health",
            subtitle = "2 blocked senders • 18 successful deliveries today",
        )
    }
}

@PreviewTest
@Preview(name = "SearchFieldLight", showBackground = true, backgroundColor = 0xFFF4F1EA, widthDp = 380)
@Composable
fun SearchFieldLightPreview() {
    PreviewFrame {
        Box(modifier = Modifier.fillMaxWidth()) {
            WorkspaceSearchField(
                query = "wechat",
                placeholder = "Search apps",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
            )
        }
    }
}

@Composable
private fun PreviewFrame(
    themeMode: Int = MagiskThemeMode.Light.value,
    content: @Composable () -> Unit,
) {
    MagiskUiKitTheme(
        themeMode = themeMode,
        dynamicColor = false,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            content()
        }
    }
}
