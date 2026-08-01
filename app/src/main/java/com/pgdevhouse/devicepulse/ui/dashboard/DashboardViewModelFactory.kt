package com.pgdevhouse.devicepulse.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pgdevhouse.devicepulse.feature.battery.BatteryRepository

/**
 * Creates DashboardViewModel instances with their required dependencies.
 */
class DashboardViewModelFactory(
    private val batteryRepository: BatteryRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(
                batteryRepository = batteryRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}