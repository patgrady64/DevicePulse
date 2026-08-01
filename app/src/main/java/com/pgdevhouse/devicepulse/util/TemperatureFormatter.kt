package com.pgdevhouse.devicepulse.util

object TemperatureFormatter {

    fun format(
        temperature: Float?
    ): String {

        if (temperature == null) {
            return "Unavailable"
        }

        return "%.1f°C".format(temperature)
    }
}