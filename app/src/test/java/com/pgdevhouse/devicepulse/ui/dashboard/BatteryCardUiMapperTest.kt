package com.pgdevhouse.devicepulse.ui.dashboard

import com.pgdevhouse.devicepulse.feature.battery.BatteryChargingStatus
import com.pgdevhouse.devicepulse.feature.battery.BatteryHealth
import com.pgdevhouse.devicepulse.feature.battery.BatteryInfo
import org.junit.Assert.assertEquals
import org.junit.Test
import com.pgdevhouse.devicepulse.feature.battery.BatteryPowerSource

class BatteryCardUiMapperTest {

    @Test
    fun `maps battery info to card ui state`() {
        val batteryInfo = BatteryInfo(
            percentage = 74,
            chargingStatus = BatteryChargingStatus.DISCHARGING,
            temperatureCelsius = 32.4f,
            health = BatteryHealth.GOOD,
            voltageMillivolts = 4_200,
            powerSource = BatteryPowerSource.USB
        )

        val result = BatteryCardUiMapper.map(
            batteryInfo = batteryInfo
        )

        assertEquals(74, result.percentage)
        assertEquals("Discharging", result.chargingStatus)
        assertEquals(32.4f, result.temperature)
        assertEquals("Good", result.condition)
        assertEquals(4_200, result.voltageMillivolts)
        assertEquals("USB", result.powerSource)
    }

    @Test
    fun `maps charging status to charging text`() {
        val result = BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = 50,
                chargingStatus = BatteryChargingStatus.CHARGING,
                temperatureCelsius = 30f,
                health = BatteryHealth.GOOD,
                voltageMillivolts = 4_200,
                powerSource = BatteryPowerSource.USB
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
                temperatureCelsius = 28f,
                health = BatteryHealth.GOOD,
                voltageMillivolts = 4_200,
                powerSource = BatteryPowerSource.USB
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
                temperatureCelsius = 29f,
                health = BatteryHealth.GOOD,
                voltageMillivolts = 4_200,
                powerSource = BatteryPowerSource.USB
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
                temperatureCelsius = null,
                health = BatteryHealth.GOOD,
                voltageMillivolts = 4_200,
                powerSource = BatteryPowerSource.USB
            )
        )

        assertEquals("Unavailable", result.chargingStatus)
        assertEquals(null, result.percentage)
        assertEquals(null, result.temperature)
    }

    @Test
    fun `maps good health to good text`() {
        val result = mapHealth(BatteryHealth.GOOD)

        assertEquals("Good", result.condition)
    }

    @Test
    fun `maps overheated health correctly`() {
        val result = mapHealth(BatteryHealth.OVERHEATED)

        assertEquals("Overheated", result.condition)
    }

    @Test
    fun `maps cold health correctly`() {
        val result = mapHealth(BatteryHealth.COLD)

        assertEquals("Cold", result.condition)
    }

    @Test
    fun `maps dead health correctly`() {
        val result = mapHealth(BatteryHealth.DEAD)

        assertEquals("Dead", result.condition)
    }

    @Test
    fun `maps over voltage health correctly`() {
        val result = mapHealth(BatteryHealth.OVER_VOLTAGE)

        assertEquals("Over voltage", result.condition)
    }

    @Test
    fun `maps failure health correctly`() {
        val result = mapHealth(BatteryHealth.FAILURE)

        assertEquals("Failure", result.condition)
    }

    @Test
    fun `maps unknown health to unavailable`() {
        val result = mapHealth(BatteryHealth.UNKNOWN)

        assertEquals("Unavailable", result.condition)
    }

    @Test
    fun `maps AC power source correctly`() {
        val result = mapPowerSource(BatteryPowerSource.AC)

        assertEquals("AC charger", result.powerSource)
    }

    @Test
    fun `maps USB power source correctly`() {
        val result = mapPowerSource(BatteryPowerSource.USB)

        assertEquals("USB", result.powerSource)
    }

    @Test
    fun `maps wireless power source correctly`() {
        val result = mapPowerSource(BatteryPowerSource.WIRELESS)

        assertEquals("Wireless", result.powerSource)
    }

    @Test
    fun `maps dock power source correctly`() {
        val result = mapPowerSource(BatteryPowerSource.DOCK)

        assertEquals("Dock", result.powerSource)
    }

    @Test
    fun `maps battery power source correctly`() {
        val result = mapPowerSource(BatteryPowerSource.BATTERY)

        assertEquals("On battery", result.powerSource)
    }

    @Test
    fun `maps unknown power source to unavailable`() {
        val result = mapPowerSource(BatteryPowerSource.UNKNOWN)

        assertEquals("Unavailable", result.powerSource)
    }

    private fun mapHealth(
        health: BatteryHealth
    ): BatteryCardUiState {
        return BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = 50,
                chargingStatus = BatteryChargingStatus.DISCHARGING,
                temperatureCelsius = 30f,
                health = health,
                voltageMillivolts = 4_200,
                powerSource = BatteryPowerSource.USB
            )
        )
    }

    private fun mapPowerSource(
        powerSource: BatteryPowerSource
    ): BatteryCardUiState {
        return BatteryCardUiMapper.map(
            batteryInfo = BatteryInfo(
                percentage = 50,
                chargingStatus = BatteryChargingStatus.CHARGING,
                temperatureCelsius = 30f,
                health = BatteryHealth.GOOD,
                voltageMillivolts = 4_200,
                powerSource = powerSource
            )
        )
    }
}