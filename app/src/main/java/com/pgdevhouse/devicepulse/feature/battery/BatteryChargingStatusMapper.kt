package com.pgdevhouse.devicepulse.feature.battery

import android.os.BatteryManager

/**
 * Converts Android battery status constants into DevicePulse values.
 */
object BatteryChargingStatusMapper {

    fun map(androidStatus: Int): BatteryChargingStatus {
        return when (androidStatus) {
            BatteryManager.BATTERY_STATUS_CHARGING ->
                BatteryChargingStatus.CHARGING

            BatteryManager.BATTERY_STATUS_DISCHARGING ->
                BatteryChargingStatus.DISCHARGING

            BatteryManager.BATTERY_STATUS_FULL ->
                BatteryChargingStatus.FULL

            BatteryManager.BATTERY_STATUS_NOT_CHARGING ->
                BatteryChargingStatus.NOT_CHARGING

            else ->
                BatteryChargingStatus.UNKNOWN
        }
    }
}