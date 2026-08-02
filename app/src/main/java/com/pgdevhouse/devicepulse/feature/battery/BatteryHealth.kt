package com.pgdevhouse.devicepulse.feature.battery

/**
 * Represents the current operating condition of the battery.
 *
 * This is DevicePulse's domain model and intentionally hides
 * Android framework constants from the rest of the application.
 */
enum class BatteryHealth {
    GOOD,
    OVERHEATED,
    COLD,
    DEAD,
    OVER_VOLTAGE,
    FAILURE,
    UNKNOWN
}