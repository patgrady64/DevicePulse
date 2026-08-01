package com.pgdevhouse.devicepulse.feature.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

/**
 * Reads current battery information from Android's battery status intent.
 */
class AndroidBatteryDataSource(
    context: Context
) : BatteryDataSource {

    private val applicationContext = context.applicationContext

    override fun getCurrentBatteryInfo(): BatteryInfo {
        val batteryIntent = applicationContext.registerReceiver(
            null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )

        return BatteryInfo(
            percentage = calculateBatteryPercentage(batteryIntent),
            chargingStatus = readChargingStatus(batteryIntent)
        )
    }

    private fun calculateBatteryPercentage(
        batteryIntent: Intent?
    ): Int? {
        if (batteryIntent == null) {
            return null
        }

        val level = batteryIntent.getIntExtra(
            BatteryManager.EXTRA_LEVEL,
            UNKNOWN_VALUE
        )

        val scale = batteryIntent.getIntExtra(
            BatteryManager.EXTRA_SCALE,
            UNKNOWN_VALUE
        )

        return BatteryPercentageCalculator.calculate(
            level = level,
            scale = scale
        )
    }
    private fun readChargingStatus(
        batteryIntent: Intent?
    ): BatteryChargingStatus {
        if (batteryIntent == null) {
            return BatteryChargingStatus.UNKNOWN
        }

        val androidStatus = batteryIntent.getIntExtra(
            BatteryManager.EXTRA_STATUS,
            BatteryManager.BATTERY_STATUS_UNKNOWN
        )

        return BatteryChargingStatusMapper.map(androidStatus)
    }


    private companion object {
        const val UNKNOWN_VALUE = -1
    }
}