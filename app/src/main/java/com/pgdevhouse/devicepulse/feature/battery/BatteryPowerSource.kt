package com.pgdevhouse.devicepulse.feature.battery

/**
 * Represents the device's current battery power source.
 */
enum class BatteryPowerSource {
    AC,
    USB,
    WIRELESS,
    DOCK,
    BATTERY,
    UNKNOWN
}