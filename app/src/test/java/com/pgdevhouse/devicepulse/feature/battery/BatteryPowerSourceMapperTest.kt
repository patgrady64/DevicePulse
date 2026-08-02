package com.pgdevhouse.devicepulse.feature.battery

import android.os.BatteryManager
import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.Test

class BatteryPowerSourceMapperTest {

    @Test
    fun `zero maps to battery`() {
        assertEquals(
            BatteryPowerSource.BATTERY,
            BatteryPowerSourceMapper.map(
                androidPowerSource = 0,
                sdkInt = Build.VERSION_CODES.TIRAMISU
            )
        )
    }

    @Test
    fun `AC maps correctly`() {
        assertEquals(
            BatteryPowerSource.AC,
            BatteryPowerSourceMapper.map(
                androidPowerSource = BatteryManager.BATTERY_PLUGGED_AC,
                sdkInt = Build.VERSION_CODES.TIRAMISU
            )
        )
    }

    @Test
    fun `USB maps correctly`() {
        assertEquals(
            BatteryPowerSource.USB,
            BatteryPowerSourceMapper.map(
                androidPowerSource = BatteryManager.BATTERY_PLUGGED_USB,
                sdkInt = Build.VERSION_CODES.TIRAMISU
            )
        )
    }

    @Test
    fun `wireless maps correctly`() {
        assertEquals(
            BatteryPowerSource.WIRELESS,
            BatteryPowerSourceMapper.map(
                androidPowerSource = BatteryManager.BATTERY_PLUGGED_WIRELESS,
                sdkInt = Build.VERSION_CODES.TIRAMISU
            )
        )
    }

    @Test
    fun `dock maps correctly on API 33 and newer`() {
        assertEquals(
            BatteryPowerSource.DOCK,
            BatteryPowerSourceMapper.map(
                androidPowerSource = BatteryManager.BATTERY_PLUGGED_DOCK,
                sdkInt = Build.VERSION_CODES.TIRAMISU
            )
        )
    }

    @Test
    fun `dock is unknown below API 33`() {
        assertEquals(
            BatteryPowerSource.UNKNOWN,
            BatteryPowerSourceMapper.map(
                androidPowerSource = BatteryManager.BATTERY_PLUGGED_DOCK,
                sdkInt = Build.VERSION_CODES.S_V2
            )
        )
    }

    @Test
    fun `unexpected value maps to unknown`() {
        assertEquals(
            BatteryPowerSource.UNKNOWN,
            BatteryPowerSourceMapper.map(
                androidPowerSource = Int.MIN_VALUE,
                sdkInt = Build.VERSION_CODES.TIRAMISU
            )
        )
    }
}