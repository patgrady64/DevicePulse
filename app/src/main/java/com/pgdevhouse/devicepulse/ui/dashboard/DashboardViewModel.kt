package com.pgdevhouse.devicepulse.ui.dashboard

import androidx.lifecycle.ViewModel

/**
 * Holds the dashboard's screen-level state and business logic.
 */
class DashboardViewModel : ViewModel() {

    val uiState: DashboardUiState = DashboardUiState()
}