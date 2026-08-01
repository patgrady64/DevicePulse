package com.pgdevhouse.devicepulse.feature.battery

/**
 * Converts Android's raw battery level and scale into a percentage.
 *
 * This class contains no Android framework code, so it can be tested
 * with fast local unit tests.
 */
object BatteryPercentageCalculator {

    fun calculate(
        level: Int,
        scale: Int
    ): Int? {
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

    private const val MIN_BATTERY_PERCENTAGE = 0
    private const val MAX_BATTERY_PERCENTAGE = 100
}