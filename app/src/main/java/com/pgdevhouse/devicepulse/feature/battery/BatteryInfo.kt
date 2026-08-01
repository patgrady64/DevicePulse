package com.pgdevhouse.devicepulse.feature.battery

/**
 * Represents battery information collected from the Android operating system.
 *
 * More battery properties will be added as DevicePulse grows.
 */
data class BatteryInfo(
    val percentage: Int?
)