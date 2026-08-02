package com.pgdevhouse.devicepulse.feature.battery

import org.junit.Assert.assertEquals
import org.junit.Test

class VoltageFormatterTest {

    @Test
    fun `formats voltage`() {
        assertEquals(
            "4.20 V",
            VoltageFormatter.format(4200)
        )
    }

    @Test
    fun `formats null voltage`() {
        assertEquals(
            "Unavailable",
            VoltageFormatter.format(null)
        )
    }

    @Test
    fun `formats lower voltage`() {
        assertEquals(
            "3.85 V",
            VoltageFormatter.format(3850)
        )
    }
}