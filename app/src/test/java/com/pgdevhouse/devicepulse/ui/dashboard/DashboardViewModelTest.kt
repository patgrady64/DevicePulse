package com.pgdevhouse.devicepulse.ui.dashboard

import com.pgdevhouse.devicepulse.feature.battery.BatteryChargingStatus
import com.pgdevhouse.devicepulse.feature.battery.BatteryHealth
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import com.pgdevhouse.devicepulse.feature.battery.BatteryInfo
import com.pgdevhouse.devicepulse.feature.battery.BatteryRepository
import com.pgdevhouse.devicepulse.feature.battery.BatteryMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.pgdevhouse.devicepulse.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

private class FakeBatteryMonitor(
    private val percentage: Int?,
    private val temperatureCelsius: Float? = 32.4f
) : BatteryMonitor {

    override fun observeBatteryInfo(): Flow<BatteryInfo> {
        return flowOf(
            BatteryInfo(
                percentage = percentage,
                chargingStatus = BatteryChargingStatus.DISCHARGING,
                temperatureCelsius = temperatureCelsius,
                health = BatteryHealth.GOOD,
                voltageMillivolts = 4_200
            )
        )
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp() {
        val repository = BatteryRepository(
            batteryMonitor = FakeBatteryMonitor(
                percentage = 74
            )
        )

        viewModel = DashboardViewModel(
            batteryRepository = repository
        )
    }

    @Test
    fun `initial state contains expected dashboard values`() {
        val state = viewModel.uiState.value

        assertEquals(
            "Everything appears normal",
            state.statusTitle
        )
        assertEquals(74, state.battery.percentage)
        assertEquals(32.4f, state.battery.temperature)
        assertEquals("82 GB", state.freeStorage)
        assertEquals("3.1 GB", state.availableMemory)

        assertEquals(
            "Discharging",
            state.battery.chargingStatus
        )
    }

    @Test
    fun `battery percentage is within valid range`() {
        val percentage = viewModel.uiState.value.battery.percentage

        assertTrue(
            "Battery percentage must be between 0 and 100",
            percentage != null && percentage in 0..100
        )
    }

    @Test
    fun `progress values are within valid range`() {
        val state = viewModel.uiState.value

        assertTrue(state.storageUsageProgress in 0f..1f)
        assertTrue(state.memoryUsageProgress in 0f..1f)
    }
}