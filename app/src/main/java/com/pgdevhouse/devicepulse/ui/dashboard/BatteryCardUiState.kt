package com.pgdevhouse.devicepulse.ui.dashboard

/**
 * UI state for the Battery dashboard card.
 *
 * This contains only information required to display the battery card.
 * It should not contain Android framework types.
 */
data class BatteryCardUiState(
    val percentage: Int? = null,
    val chargingStatus: String = "Unavailable",
    val temperature: Float? = null,
    val condition: String = "Unknown",
    val voltageMillivolts: Int? = null
)