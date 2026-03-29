package io.github.magisk317.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import io.github.magisk317.uikit.preference.SectionCard
import io.github.magisk317.uikit.preference.StateSwitchItem
import io.github.magisk317.uikit.surface.AppBottomNavigationBar
import io.github.magisk317.uikit.surface.AppNavigationItemSpec
import io.github.magisk317.uikit.surface.ExpressiveHeroCard
import io.github.magisk317.uikit.surface.SummaryRow
import io.github.magisk317.uikit.surface.SummarySectionCard
import io.github.magisk317.uikit.surface.WorkspaceSearchField
import io.github.magisk317.uikit.theme.MagiskThemeMode
import io.github.magisk317.uikit.theme.MagiskUiKitTheme
import io.github.magisk317.uikit.theme.UiKitStyle

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

@PreviewTest
@Preview(name = "SectionCardMiuixLight", showBackground = true, backgroundColor = 0xFFF4F1EA, widthDp = 380)
@Composable
fun SectionCardMiuixLightPreview() {
    PreviewFrame(uiKitStyle = UiKitStyle.Miuix) {
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
@Preview(name = "SearchFieldMiuixLight", showBackground = true, backgroundColor = 0xFFF4F1EA, widthDp = 380)
@Composable
fun SearchFieldMiuixLightPreview() {
    PreviewFrame(uiKitStyle = UiKitStyle.Miuix) {
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

@PreviewTest
@Preview(name = "BottomNavMiuixLight", showBackground = true, backgroundColor = 0xFFF4F1EA, widthDp = 380)
@Composable
fun BottomNavMiuixLightPreview() {
    PreviewFrame(uiKitStyle = UiKitStyle.Miuix) {
        AppBottomNavigationBar(
            items = listOf(
                AppNavigationItemSpec("Overview", Icons.Default.Home, true, {}),
                AppNavigationItemSpec("Records", Icons.Default.History, false, {}),
                AppNavigationItemSpec("Settings", Icons.Default.Settings, false, {}),
            ),
        )
    }
}

@PreviewTest
@Preview(name = "SummaryCardMiuixLight", showBackground = true, backgroundColor = 0xFFF4F1EA, widthDp = 380)
@Composable
fun SummaryCardMiuixLightPreview() {
    PreviewFrame(uiKitStyle = UiKitStyle.Miuix) {
        SummarySectionCard {
            SummaryRow(
                icon = Icons.Default.Home,
                label = "Version",
                value = "3.2.5",
            )
            SummaryRow(
                icon = Icons.Default.Settings,
                label = "Framework",
                value = "LSPosed",
            )
        }
    }
}

@Composable
private fun PreviewFrame(
    themeMode: Int = MagiskThemeMode.Light.value,
    uiKitStyle: UiKitStyle = UiKitStyle.Expressive,
    content: @Composable () -> Unit,
) {
    MagiskUiKitTheme(
        themeMode = themeMode,
        dynamicColor = false,
        uiKitStyle = uiKitStyle,
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
