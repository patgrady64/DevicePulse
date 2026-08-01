package com.pgdevhouse.devicepulse.feature.battery

/**
 * Represents battery information collected from Android.
 */
data class BatteryInfo(
    val percentage: Int?,
    val chargingStatus: BatteryChargingStatus,
    val temperatureCelsius: Float?
)