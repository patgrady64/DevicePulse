package com.pgdevhouse.devicepulse.feature.battery

/**
 * Represents the device's current battery charging state.
 */
enum class BatteryChargingStatus {
    CHARGING,
    DISCHARGING,
    FULL,
    NOT_CHARGING,
    UNKNOWN
}