package com.pgdevhouse.devicepulse.ui.dashboard

import com.pgdevhouse.devicepulse.feature.battery.BatteryChargingStatus
import com.pgdevhouse.devicepulse.feature.battery.BatteryInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class BatteryCardUiMapperTest {

    @Test
    fun `maps battery info to card ui state`() {
        val batteryInfo = BatteryInfo(
            percentage = 74,
            chargingStatus = BatteryChargingStatus.DISCHARGING,
            temperatureCelsius = 32.4f
        )

        val result = BatteryCardUiMapper.map(
            batteryInfo = batteryInfo
        )

        assertEquals(74, result.percentage)
        assertEquals("Discharging", result.chargingStatus)
        assertEquals(32.4f, result.temperature)
        assertEquals("Good", result.condition)
    }

    @Test
    fun `maps charging status to charging text`() {
        val result = BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = 50,
                chargingStatus = BatteryChargingStatus.CHARGING,
                temperatureCelsius = 30f
            )
        )

        assertEquals("Charging", result.chargingStatus)
    }

    @Test
    fun `maps full status to full text`() {
        val result = BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = 100,
                chargingStatus = BatteryChargingStatus.FULL,
                temperatureCelsius = 28f
            )
        )

        assertEquals("Full", result.chargingStatus)
    }

    @Test
    fun `maps not charging status correctly`() {
        val result = BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = 80,
                chargingStatus = BatteryChargingStatus.NOT_CHARGING,
                temperatureCelsius = 29f
            )
        )

        assertEquals("Not charging", result.chargingStatus)
    }

    @Test
    fun `maps unknown status to unavailable`() {
        val result = BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = null,
                chargingStatus = BatteryChargingStatus.UNKNOWN,
                temperatureCelsius = null
            )
        )

        assertEquals("Unavailable", result.chargingStatus)
        assertEquals(null, result.percentage)
        assertEquals(null, result.temperature)
    }
}