package com.pgdevhouse.devicepulse.feature.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Converts Android battery broadcasts into a Flow of BatteryInfo objects.
 */
class AndroidBatteryMonitor(
    context: Context
) : BatteryMonitor {

    private val applicationContext = context.applicationContext

    override fun observeBatteryInfo(): Flow<BatteryInfo> = callbackFlow {

        val receiver = object : BroadcastReceiver() {

            override fun onReceive(
                context: Context?,
                intent: Intent?
            ) {

                if (intent == null) return

                val level = intent.getIntExtra(
                    BatteryManager.EXTRA_LEVEL,
                    -1
                )

                val scale = intent.getIntExtra(
                    BatteryManager.EXTRA_SCALE,
                    -1
                )

                val status = intent.getIntExtra(
                    BatteryManager.EXTRA_STATUS,
                    BatteryManager.BATTERY_STATUS_UNKNOWN
                )

                trySend(
                    BatteryInfo(
                        percentage = BatteryPercentageCalculator.calculate(
                            level = level,
                            scale = scale
                        ),
                        chargingStatus =
                            BatteryChargingStatusMapper.map(status),
                        temperatureCelsius =
                            readTemperature(intent)
                    )
                )
            }
        }

        applicationContext.registerReceiver(
            receiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )

        awaitClose {
            applicationContext.unregisterReceiver(receiver)
        }
    }
    private fun readTemperature(
        intent: Intent
    ): Float? {

        val rawTemperature = intent.getIntExtra(
            BatteryManager.EXTRA_TEMPERATURE,
            Int.MIN_VALUE
        )

        if (rawTemperature == Int.MIN_VALUE) {
            return null
        }

        return rawTemperature / 10f
    }

}