package com.pgdevhouse.devicepulse.feature.battery

/**
 * Defines how DevicePulse obtains current battery information.
 *
 * The interface allows the dashboard to use either real Android battery data
 * or predictable fake data during tests.
 */
interface BatteryDataSource {

    fun getCurrentBatteryInfo(): BatteryInfo
}