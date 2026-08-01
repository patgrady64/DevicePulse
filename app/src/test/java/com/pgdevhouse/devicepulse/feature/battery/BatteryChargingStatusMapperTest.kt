package com.pgdevhouse.devicepulse.feature.battery

import android.os.BatteryManager
import org.junit.Assert.assertEquals
import org.junit.Test

class BatteryChargingStatusMapperTest {

    @Test
    fun `charging Android status maps to charging`() {
        val result = BatteryChargingStatusMapper.map(
            BatteryManager.BATTERY_STATUS_CHARGING
        )

        assertEquals(BatteryChargingStatus.CHARGING, result)
    }

    @Test
    fun `discharging Android status maps to discharging`() {
        val result = BatteryChargingStatusMapper.map(
            BatteryManager.BATTERY_STATUS_DISCHARGING
        )

        assertEquals(BatteryChargingStatus.DISCHARGING, result)
    }

    @Test
    fun `full Android status maps to full`() {
        val result = BatteryChargingStatusMapper.map(
            BatteryManager.BATTERY_STATUS_FULL
        )

        assertEquals(BatteryChargingStatus.FULL, result)
    }

    @Test
    fun `not charging Android status maps correctly`() {
        val result = BatteryChargingStatusMapper.map(
            BatteryManager.BATTERY_STATUS_NOT_CHARGING
        )

        assertEquals(BatteryChargingStatus.NOT_CHARGING, result)
    }

    @Test
    fun `unknown value maps to unknown`() {
        val result = BatteryChargingStatusMapper.map(
            Int.MIN_VALUE
        )

        assertEquals(BatteryChargingStatus.UNKNOWN, result)
    }
}