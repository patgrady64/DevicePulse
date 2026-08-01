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
            percentage = calculateBatteryPercentage(batteryIntent)
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

        if (level < 0 || scale <= 0) {
            return null
        }

        return ((level.toFloat() / scale.toFloat()) * 100)
            .toInt()
            .coerceIn(
                minimumValue = MIN_BATTERY_PERCENTAGE,
                maximumValue = MAX_BATTERY_PERCENTAGE
            )
    }

    private companion object {
        const val UNKNOWN_VALUE = -1
        const val MIN_BATTERY_PERCENTAGE = 0
        const val MAX_BATTERY_PERCENTAGE = 100
    }
}