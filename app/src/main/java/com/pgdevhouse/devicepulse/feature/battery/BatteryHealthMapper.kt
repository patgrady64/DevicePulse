package com.pgdevhouse.devicepulse.feature.battery

import android.os.BatteryManager

/**
 * Converts Android battery-health constants into DevicePulse domain values.
 */
object BatteryHealthMapper {

    fun map(
        androidHealth: Int
    ): BatteryHealth {
        return when (androidHealth) {
            BatteryManager.BATTERY_HEALTH_GOOD ->
                BatteryHealth.GOOD

            BatteryManager.BATTERY_HEALTH_OVERHEAT ->
                BatteryHealth.OVERHEATED

            BatteryManager.BATTERY_HEALTH_COLD ->
                BatteryHealth.COLD

            BatteryManager.BATTERY_HEALTH_DEAD ->
                BatteryHealth.DEAD

            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE ->
                BatteryHealth.OVER_VOLTAGE

            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE ->
                BatteryHealth.FAILURE

            else ->
                BatteryHealth.UNKNOWN
        }
    }
}