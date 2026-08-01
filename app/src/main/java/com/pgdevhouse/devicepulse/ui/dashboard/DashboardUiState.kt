package com.pgdevhouse.devicepulse.ui.dashboard

/**
 * Represents everything currently displayed on the dashboard.
 *
 * The values are placeholders until DevicePulse begins reading real
 * information from Android system services.
 */
data class DashboardUiState(
    val statusTitle: String = "Everything appears normal",
    val statusMessage: String =
        "Battery temperature, storage, and memory are within normal ranges.",

    val batteryPercentage: Int = 74,
    val batteryTemperature: String = "32.4°C",
    val batteryChargingStatus: String = "Discharging",
    val batteryCondition: String = "Good",

    val usedStorage: String = "174 GB",
    val freeStorage: String = "82 GB",
    val totalStorage: String = "256 GB",
    val storageUsageProgress: Float = 0.68f,

    val usedMemory: String = "4.9 GB",
    val availableMemory: String = "3.1 GB",
    val totalMemory: String = "8 GB",
    val memoryUsageProgress: Float = 0.61f,

    val manufacturer: String = "Samsung",
    val model: String = "Galaxy Device",
    val androidVersion: String = "Android 16",
    val uptime: String = "3 days, 7 hours"
)