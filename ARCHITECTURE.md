# DevicePulse Architecture

This document defines the architectural rules and development practices used by DevicePulse.

The goal is to keep the project understandable, testable, and maintainable as it grows.

## Core Principles

### 1. Single-Activity Architecture

DevicePulse uses one Android `Activity`.

`MainActivity` is responsible only for:

* Starting Jetpack Compose
* Applying the DevicePulse theme
* Launching the root `DevicePulseApp` composable

Additional screens should be implemented as Compose destinations rather than separate Android activities unless a future Android requirement makes another activity necessary.

### 2. Feature-First Organization

Code should be grouped by feature rather than only by technical type.

Example:

```text
com.pgdevhouse.devicepulse
├── feature
│   ├── battery
│   ├── storage
│   ├── memory
│   └── device
├── ui
│   ├── dashboard
│   ├── settings
│   └── theme
├── DevicePulseApp.kt
└── MainActivity.kt
```

A feature package may contain its own:

* Models
* Data sources
* Repositories
* Calculators
* Mappers
* Tests

This keeps related code together and makes features easier to locate.

### 3. One Responsibility Per Class

Each class or function should have one clear purpose.

Examples:

* `BatteryCard` displays battery information.
* `BatteryPercentageCalculator` converts raw values into a percentage.
* `AndroidBatteryDataSource` reads battery information from Android.
* `DashboardViewModel` manages dashboard state.
* `DashboardScreen` displays dashboard state.

UI components must not directly read Android system information.

### 4. Clear Data Flow

DevicePulse should generally follow this flow:

```text
Android system API
        ↓
Data source
        ↓
Repository
        ↓
ViewModel
        ↓
UI state
        ↓
Compose screen
```

The UI should display state and send user actions upward.

The UI should not know how device information was collected.

### 5. Immutable UI State

Screen state should be represented by immutable Kotlin data classes.

Example:

```kotlin
data class DashboardUiState(
    val batteryPercentage: Int?,
    val isLoading: Boolean,
    val errorMessage: String?
)
```

A screen should receive state as a parameter whenever practical.

This makes screens easier to preview and test.

### 6. Route and Screen Separation

Screen-level Compose features should generally use two composables:

```text
DashboardRoute
DashboardScreen
```

`DashboardRoute` may:

* Obtain the ViewModel
* Collect state
* Handle navigation
* Connect callbacks

`DashboardScreen` should:

* Receive plain state
* Receive event callbacks
* Render the interface

The pure screen composable should not obtain its own ViewModel.

### 7. Android APIs Stay Outside the UI

Android framework APIs such as:

* `BatteryManager`
* `ActivityManager`
* `StorageManager`
* `UsageStatsManager`
* `BroadcastReceiver`

must not be called directly from composables.

They belong in Android-specific data sources or services.

### 8. Testable Logic Should Be Pure Kotlin

Calculations, formatting, mapping, thresholds, and scoring logic should be separated from Android framework code whenever possible.

For example:

```text
BatteryPercentageCalculator
TemperatureFormatter
StorageUsageCalculator
PulseScoreCalculator
```

Pure Kotlin code can be tested quickly with local unit tests.

### 9. Testing Requirements

DevicePulse uses three levels of testing.

#### Local Unit Tests

Location:

```text
app/src/test/
```

Use these for:

* Calculations
* Formatting
* Mapping
* ViewModel behavior
* Repository behavior with fake data sources
* Warning thresholds
* Pulse Score logic

#### Android Integration Tests

Location:

```text
app/src/androidTest/
```

Use these when Android framework behavior is required.

Examples:

* Reading Android system services
* Interpreting Android intents
* Testing device-dependent behavior

#### Compose UI Tests

Use these to verify:

* Important text is displayed
* Loading states appear
* Error states appear
* Buttons perform expected actions
* Screen content reacts to supplied state

### 10. Every Completed Feature Must Be Verified

Before a feature is considered complete:

1. The project must compile.
2. The app must launch.
3. The feature must work on a phone or emulator.
4. Relevant unit tests must pass.
5. Existing tests must still pass.
6. Errors and unavailable values must be handled safely.
7. The changelog must be updated when appropriate.
8. A Git commit should be created.

### 11. Commit Only Working Milestones

Commits should represent completed, working changes.

Avoid committing partially implemented features unless necessary for recovery or collaboration.

Preferred commit style:

```text
feat: add live battery percentage to dashboard
fix: handle unavailable battery scale
test: add battery percentage calculation tests
refactor: separate dashboard route from screen
docs: add project architecture guidelines
```

### 12. No Fake Optimization Claims

DevicePulse must remain honest about what Android permits.

The app must not claim to:

* Magically increase battery capacity
* Clean RAM to permanently improve performance
* Cool a device through software
* Extend battery life without a measurable mechanism
* Repair battery hardware
* Force-close unrelated applications without user action

DevicePulse should explain device information and provide evidence-based guidance.

### 13. No Magic Numbers

Thresholds and limits should use clearly named constants.

Avoid:

```kotlin
if (temperature > 40f)
```

Prefer:

```kotlin
private const val HIGH_BATTERY_TEMPERATURE_CELSIUS = 40f
```

Thresholds that may become configurable should eventually live in a settings or configuration model.

### 14. Handle Missing Information Safely

Android devices do not all expose the same information.

DevicePulse must expect values to be:

* Missing
* Unsupported
* Unknown
* Manufacturer-specific
* Temporarily unavailable

The app should display a clear unavailable state instead of inventing a value or crashing.

### 15. Documentation

Public classes and important functions should include a short KDoc comment explaining their responsibility.

Comments should explain why code exists when the reason is not obvious.

Comments should not merely repeat what the code already says.

## Current Data Flow

The initial dashboard architecture is:

```text
MainActivity
    ↓
DevicePulseApp
    ↓
DashboardRoute
    ↓
DashboardViewModel
    ↓
DashboardUiState
    ↓
DashboardScreen
    ↓
Dashboard cards
```

The battery feature currently contains:

```text
feature/battery
├── AndroidBatteryDataSource.kt
├── BatteryDataSource.kt
├── BatteryInfo.kt
└── BatteryPercentageCalculator.kt
```

## Long-Term Direction

DevicePulse is intended to grow into an honest Android device analytics utility covering:

* Battery condition and charging behavior
* Battery temperature history
* Storage usage and trends
* Memory information
* Device details
* Usage insights
* Device timeline
* Notifications
* Widgets
* Weekly reports
* Pulse Score

New features should follow the principles in this document so the project remains understandable as it grows.
