package com.pgdevhouse.devicepulse.ui.dashboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp() {
        viewModel = DashboardViewModel()
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