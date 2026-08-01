package com.pgdevhouse.devicepulse.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pgdevhouse.devicepulse.ui.theme.DevicePulseTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.pgdevhouse.devicepulse.feature.battery.AndroidBatteryMonitor
import com.pgdevhouse.devicepulse.feature.battery.BatteryRepository
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.pgdevhouse.devicepulse.util.TemperatureFormatter

/**
 * Connects the dashboard UI to its screen-level ViewModel.
 */
@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val batteryRepository = BatteryRepository(
        batteryMonitor = AndroidBatteryMonitor(context)
    )

    val viewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModelFactory(
            batteryRepository = batteryRepository
        )
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreen(
        uiState = uiState,
        modifier = modifier
    )
}

/**
 * Displays the dashboard using the supplied immutable UI state.
 *
 * Keeping this composable independent of the ViewModel makes it easier
 * to preview and test.
 */
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardHeader()

            StatusSummaryCard(
                title = uiState.statusTitle,
                message = uiState.statusMessage
            )

            BatteryCard(
                batteryPercentage = uiState.batteryPercentage,
                temperature = uiState.batteryTemperature,
                chargingStatus = uiState.batteryChargingStatus,
                batteryCondition = uiState.batteryCondition
            )

            StorageCard(
                usedStorage = uiState.usedStorage,
                freeStorage = uiState.freeStorage,
                totalStorage = uiState.totalStorage,
                usageProgress = uiState.storageUsageProgress
            )

            MemoryCard(
                usedMemory = uiState.usedMemory,
                availableMemory = uiState.availableMemory,
                totalMemory = uiState.totalMemory,
                usageProgress = uiState.memoryUsageProgress
            )

            DeviceCard(
                manufacturer = uiState.manufacturer,
                model = uiState.model,
                androidVersion = uiState.androidVersion,
                uptime = uiState.uptime
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun DashboardHeader() {
    Column {
        Text(
            text = "DevicePulse",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Your device right now",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StatusSummaryCard(
    title: String,
    message: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun BatteryCard(
    batteryPercentage: Int?,
    temperature: Float?,
    chargingStatus: String,
    batteryCondition: String
) {
    DashboardCard(
        title = "Battery"
    ) {
        Text(
            text = batteryPercentage?.let { "$it%" } ?: "Unavailable",
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (batteryPercentage != null) {
            LinearProgressIndicator(
                progress = { batteryPercentage / 100f },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        InformationRow(
            label = "Status",
            value = chargingStatus
        )

        InformationRow(
            label = "Temperature",
            value = TemperatureFormatter.format(
                temperature
            )
        )

        InformationRow(
            label = "Condition",
            value = batteryCondition
        )
    }
}

@Composable
private fun StorageCard(
    usedStorage: String,
    freeStorage: String,
    totalStorage: String,
    usageProgress: Float
) {
    DashboardCard(
        title = "Storage"
    ) {
        Text(
            text = "$freeStorage free",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "$usedStorage used of $totalStorage",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(
            progress = { usageProgress },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MemoryCard(
    usedMemory: String,
    availableMemory: String,
    totalMemory: String,
    usageProgress: Float
) {
    DashboardCard(
        title = "Memory"
    ) {
        Text(
            text = "$availableMemory available",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "$usedMemory used of $totalMemory",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(
            progress = { usageProgress },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DeviceCard(
    manufacturer: String,
    model: String,
    androidVersion: String,
    uptime: String
) {
    DashboardCard(
        title = "Device"
    ) {
        InformationRow(
            label = "Manufacturer",
            value = manufacturer
        )

        InformationRow(
            label = "Model",
            value = model
        )

        InformationRow(
            label = "System",
            value = androidVersion
        )

        InformationRow(
            label = "Uptime",
            value = uptime
        )
    }
}

@Composable
private fun DashboardCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))

            content()
        }
    }
}

@Composable
private fun InformationRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun DashboardScreenPreview() {
    DevicePulseTheme {
        DashboardScreen(
            uiState = DashboardUiState()
        )
    }
}
