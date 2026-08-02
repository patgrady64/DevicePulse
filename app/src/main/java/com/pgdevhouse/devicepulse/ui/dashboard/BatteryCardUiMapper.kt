package com.pgdevhouse.devicepulse.ui.dashboard

import com.pgdevhouse.devicepulse.feature.battery.BatteryChargingStatus
import com.pgdevhouse.devicepulse.feature.battery.BatteryInfo

/**
 * Converts battery domain models into UI state.
 *
 * The mapper is responsible for presentation decisions only.
 * It should not access Android APIs or perform business logic.
 */
object BatteryCardUiMapper {

    fun map(
        batteryInfo: BatteryInfo
    ): BatteryCardUiState {

        return BatteryCardUiState(
            percentage = batteryInfo.percentage,
            chargingStatus = batteryInfo.chargingStatus.toDisplayText(),
            temperature = batteryInfo.temperatureCelsius,

            // Temporary until Android battery health is implemented.
            condition = "Good"
        )
    }
}

private fun BatteryChargingStatus.toDisplayText(): String {

    return when (this) {

        BatteryChargingStatus.CHARGING ->
            "Charging"

        BatteryChargingStatus.DISCHARGING ->
            "Discharging"

        BatteryChargingStatus.FULL ->
            "Full"

        BatteryChargingStatus.NOT_CHARGING ->
            "Not charging"

        BatteryChargingStatus.UNKNOWN ->
            "Unavailable"
    }
}