# Changelog

All notable changes to DevicePulse will be recorded in this file.

DevicePulse follows a milestone-based development process. Versions may remain in development until the related feature set is complete and verified.

## Unreleased

### Added

* Initial DevicePulse Android project
* Jetpack Compose and Material 3 interface
* Root `DevicePulseApp` composable
* Single-activity application structure
* Dashboard screen with battery, storage, memory, and device cards
* Immutable `DashboardUiState`
* `DashboardViewModel`
* Route and screen separation for the dashboard
* Feature-first package organization
* Battery information model
* Battery data-source interface
* Android battery data source
* Pure Kotlin battery percentage calculator
* Dashboard ViewModel unit tests
* Battery percentage calculator unit tests
* Project architecture documentation
* - Live battery percentage updates
- Live charging-status updates
- Live battery temperature data
- Reactive battery monitoring with Flow and StateFlow
- Reusable coroutine Main dispatcher rule for ViewModel tests

### Changed

* Reduced `MainActivity` to application startup responsibilities
* Moved dashboard UI out of `MainActivity`
* Replaced hardcoded dashboard ownership with state supplied through the ViewModel
* Added explicit Material theme background handling
* - Grouped battery dashboard values into `BatteryCardUiState`
- Simplified `BatteryCard` to receive a single state object
- Updated dashboard ViewModel tests for feature-based UI state

### Fixed

* Corrected dashboard composable invocation that caused a blank screen
* Corrected test package structure so package-level test runs work
* Corrected battery percentage constants so calculation tests compile

## Planned

### Live Battery Monitoring

* Display the device’s real battery percentage
* Display charging or discharging status
* Display the current power source
* Display battery temperature
* Display battery voltage
* Display Android-reported battery health
* Refresh battery information while the app is active
* Add tests for battery status mappings
* Add tests for unsupported and unavailable battery values

### Device Information

* Manufacturer
* Model
* Android version
* Device uptime
* Last reboot estimate

### Memory

* Total memory
* Available memory
* Used memory
* Low-memory state

### Storage

* Total internal storage
* Used internal storage
* Free internal storage
* Storage usage percentage

### Future Features

* Battery history
* Charging-session history
* Temperature trends
* Device timeline
* Pulse Score
* Storage trends
* Usage insights
* Notifications
* Home-screen widgets
* Reports
* Settings
