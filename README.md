# magisk-ui-kit

Shared Android Compose UI primitives for the Magisk317 app family.

Current extracted surface:

- `io.github.magisk317.uikit.theme`
  - `MagiskUiKitTheme`
  - `MagiskThemeMode`
  - `UpdateSystemBars`
  - `SystemBarsScrim`
  - `rememberHazeStyle`
  - `Spacing`
- `io.github.magisk317.uikit.foundation`
  - `BasePresenter`
  - `BaseView`
  - `TextWatcherAdapter`
  - `BaseItemCallback`
  - `ItemCallback`
  - `ItemChildCallback`
  - `ImmutableListWrapper`
  - `LoadingIndicatorTokens`
  - `rememberMinDurationLoading`
  - `AppLinearLoadingIndicator`
  - `PolygonMorphLoadingIndicator`
  - `LocalSnackbarHostState`
  - `SessionLoadingRegistry`
- `io.github.magisk317.uikit.shell`
  - `BaseActivity`
  - `PlayUpdateDelegate`
  - `AppInfoHelper`
  - `RecordItem`
- `io.github.magisk317.uikit.common`
  - `DismissibleSnackbarHost`
- `io.github.magisk317.uikit.preference`
  - `SectionCard`
  - `Item`
  - `StateSwitchItem`
  - `ActionSwitchItem`
- `io.github.magisk317.uikit.surface`
  - `ExpressiveHeroCard`
  - `ExpressiveSectionCard`
  - `FeatureEntryCard`
  - `MetricCard`
  - `MetricGrid`
  - `MetricSpec`
  - `InfoPill`
  - `DetailSectionCard`
  - `DetailRow`
  - `DetailDivider`
  - `OverlayHeaderScaffold`
  - `OverlayHeaderPanel`
  - `SearchWorkspaceScaffold`
  - `WorkspaceTopBarSearchOverlay`
  - `WorkspaceSearchField`
  - `WorkspaceListItem`
  - `WorkspaceEmptyState`
  - `WorkspaceTrailingIcon`
  - `LabelValueBlock`
  - `ActionStrip`
  - `SectionColumn`

## Include from another repo

Recommended: add it as a git submodule in the parent repository root.

```bash
git submodule add https://github.com/magisk317/magisk-ui-kit.git magisk-ui-kit
```

Then in `settings.gradle.kts`:

```kotlin
include(":magisk-ui-kit")
```

Then in the target module:

```kotlin
dependencies {
    implementation(project(":magisk-ui-kit"))
}
```
