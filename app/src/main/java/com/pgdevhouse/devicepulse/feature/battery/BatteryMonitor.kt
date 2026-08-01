package com.pgdevhouse.devicepulse.feature.battery

import kotlinx.coroutines.flow.Flow

/**
 * Continuously observes battery information reported by Android.
 */
interface BatteryMonitor {

    /**
     * Emits an updated BatteryInfo whenever Android reports a battery change.
     */
    fun observeBatteryInfo(): Flow<BatteryInfo>
}