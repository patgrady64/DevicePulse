package com.pgdevhouse.devicepulse.ui.dashboard

import com.pgdevhouse.devicepulse.feature.battery.BatteryChargingStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import com.pgdevhouse.devicepulse.feature.battery.BatteryDataSource
import com.pgdevhouse.devicepulse.feature.battery.BatteryInfo
import com.pgdevhouse.devicepulse.feature.battery.BatteryRepository

private class FakeBatteryDataSource(
    private val percentage: Int?
) : BatteryDataSource {

    override fun getCurrentBatteryInfo(): BatteryInfo {
        return BatteryInfo(
            percentage = percentage,
            chargingStatus = BatteryChargingStatus.DISCHARGING
        )
    }
}


class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp() {
        val repository = BatteryRepository(
            batteryDataSource = FakeBatteryDataSource(
                percentage = 74
            )
        )

        viewModel = DashboardViewModel(
            batteryRepository = repository
        )
    }

    @Test
    fun `initial state contains expected dashboard values`() {
        val state = viewModel.uiState

        assertEquals(
            "Everything appears normal",
            state.statusTitle
        )
        assertEquals(74, state.batteryPercentage)
        assertEquals("32.4°C", state.batteryTemperature)
        assertEquals("82 GB", state.freeStorage)
        assertEquals("3.1 GB", state.availableMemory)

        assertEquals(
            "Discharging",
            state.batteryChargingStatus
        )
    }

    @Test
    fun `battery percentage is within valid range`() {
        val percentage = viewModel.uiState.batteryPercentage

        assertTrue(
            "Battery percentage must be between 0 and 100",
            percentage in 0..100
        )
    }

    @Test
    fun `progress values are within valid range`() {
        val state = viewModel.uiState

        assertTrue(state.storageUsageProgress in 0f..1f)
        assertTrue(state.memoryUsageProgress in 0f..1f)
    }
}