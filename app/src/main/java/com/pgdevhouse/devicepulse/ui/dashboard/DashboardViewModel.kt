package com.pgdevhouse.devicepulse.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgdevhouse.devicepulse.feature.battery.BatteryChargingStatus
import com.pgdevhouse.devicepulse.feature.battery.BatteryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Holds the dashboard's screen-level state and observes live device data.
 */
class DashboardViewModel(
    private val batteryRepository: BatteryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        DashboardUiState()
    )

    val uiState: StateFlow<DashboardUiState> =
        _uiState.asStateFlow()

    init {
        observeBattery()
    }

    private fun observeBattery() {
        viewModelScope.launch {
            batteryRepository
                .observeBatteryInfo()
                .collectLatest { batteryInfo ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            batteryPercentage = batteryInfo.percentage,
                            batteryChargingStatus =
                                batteryInfo.chargingStatus.toDisplayText()
                        )
                    }
                }
        }
    }
}

private fun BatteryChargingStatus.toDisplayText(): String {
    return when (this) {
        BatteryChargingStatus.CHARGING -> "Charging"
        BatteryChargingStatus.DISCHARGING -> "Discharging"
        BatteryChargingStatus.FULL -> "Full"
        BatteryChargingStatus.NOT_CHARGING -> "Not charging"
        BatteryChargingStatus.UNKNOWN -> "Unavailable"
    }
}