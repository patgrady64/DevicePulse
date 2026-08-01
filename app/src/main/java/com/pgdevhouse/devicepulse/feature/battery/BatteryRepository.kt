package com.pgdevhouse.devicepulse.feature.battery

import kotlinx.coroutines.flow.Flow

/**
 * Provides battery information to the rest of the application.
 */
class BatteryRepository(
    private val batteryMonitor: BatteryMonitor
) {

    fun observeBatteryInfo(): Flow<BatteryInfo> {
        return batteryMonitor.observeBatteryInfo()
    }
}