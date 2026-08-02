package com.pgdevhouse.devicepulse.feature.battery

import android.os.BatteryManager
import android.os.Build

/**
 * Converts Android plugged-source constants into DevicePulse domain values.
 */
object BatteryPowerSourceMapper {

    fun map(
        androidPowerSource: Int,
        sdkInt: Int = Build.VERSION.SDK_INT
    ): BatteryPowerSource {
        return when {
            androidPowerSource == BATTERY_POWER ->
                BatteryPowerSource.BATTERY

            androidPowerSource == BatteryManager.BATTERY_PLUGGED_AC ->
                BatteryPowerSource.AC

            androidPowerSource == BatteryManager.BATTERY_PLUGGED_USB ->
                BatteryPowerSource.USB

            androidPowerSource == BatteryManager.BATTERY_PLUGGED_WIRELESS ->
                BatteryPowerSource.WIRELESS

            sdkInt >= Build.VERSION_CODES.TIRAMISU &&
                    androidPowerSource == BatteryManager.BATTERY_PLUGGED_DOCK ->
                BatteryPowerSource.DOCK

            else ->
                BatteryPowerSource.UNKNOWN
        }
    }

    private const val BATTERY_POWER = 0
}