package com.pgdevhouse.devicepulse.feature.battery

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BatteryPercentageCalculatorTest {

    @Test
    fun `full battery returns one hundred percent`() {
        val result = BatteryPercentageCalculator.calculate(
            level = 100,
            scale = 100
        )

        assertEquals(100, result)
    }

    @Test
    fun `partial battery level is converted to percentage`() {
        val result = BatteryPercentageCalculator.calculate(
            level = 37,
            scale = 50
        )

        assertEquals(74, result)
    }

    @Test
    fun `different scale is converted correctly`() {
        val result = BatteryPercentageCalculator.calculate(
            level = 750,
            scale = 1000
        )

        assertEquals(75, result)
    }

    @Test
    fun `negative level returns unavailable`() {
        val result = BatteryPercentageCalculator.calculate(
            level = -1,
            scale = 100
        )

        assertNull(result)
    }

    @Test
    fun `zero scale returns unavailable`() {
        val result = BatteryPercentageCalculator.calculate(
            level = 50,
            scale = 0
        )

        assertNull(result)
    }

    @Test
    fun `negative scale returns unavailable`() {
        val result = BatteryPercentageCalculator.calculate(
            level = 50,
            scale = -1
        )

        assertNull(result)
    }

    @Test
    fun `percentage cannot exceed one hundred`() {
        val result = BatteryPercentageCalculator.calculate(
            level = 150,
            scale = 100
        )

        assertEquals(100, result)
    }
}