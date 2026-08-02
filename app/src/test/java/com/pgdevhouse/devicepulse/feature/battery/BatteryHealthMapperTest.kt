package com.pgdevhouse.devicepulse.feature.battery

import android.os.BatteryManager
import org.junit.Assert.assertEquals
import org.junit.Test

class BatteryHealthMapperTest {

    @Test
    fun `good Android health maps to good`() {
        assertEquals(
            BatteryHealth.GOOD,
            BatteryHealthMapper.map(
                BatteryManager.BATTERY_HEALTH_GOOD
            )
        )
    }

    @Test
    fun `overheat Android health maps to overheated`() {
        assertEquals(
            BatteryHealth.OVERHEATED,
            BatteryHealthMapper.map(
                BatteryManager.BATTERY_HEALTH_OVERHEAT
            )
        )
    }

    @Test
    fun `cold Android health maps to cold`() {
        assertEquals(
            BatteryHealth.COLD,
            BatteryHealthMapper.map(
                BatteryManager.BATTERY_HEALTH_COLD
            )
        )
    }

    @Test
    fun `dead Android health maps to dead`() {
        assertEquals(
            BatteryHealth.DEAD,
            BatteryHealthMapper.map(
                BatteryManager.BATTERY_HEALTH_DEAD
            )
        )
    }

    @Test
    fun `over voltage Android health maps correctly`() {
        assertEquals(
            BatteryHealth.OVER_VOLTAGE,
            BatteryHealthMapper.map(
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE
            )
        )
    }

    @Test
    fun `failure Android health maps correctly`() {
        assertEquals(
            BatteryHealth.FAILURE,
            BatteryHealthMapper.map(
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE
            )
        )
    }

    @Test
    fun `unknown Android value maps to unknown`() {
        assertEquals(
            BatteryHealth.UNKNOWN,
            BatteryHealthMapper.map(
                Int.MIN_VALUE
            )
        )
    }
}