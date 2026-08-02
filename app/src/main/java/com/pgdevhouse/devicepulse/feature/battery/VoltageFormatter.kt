package com.pgdevhouse.devicepulse.feature.battery

import java.util.Locale

/**
 * Formats battery voltage for display.
 */
object VoltageFormatter {

    fun format(
        millivolts: Int?
    ): String {

        if (millivolts == null) {
            return "Unavailable"
        }

        return String.format(
            Locale.US,
            "%.2f V",
            millivolts / 1000.0
        )
    }
}