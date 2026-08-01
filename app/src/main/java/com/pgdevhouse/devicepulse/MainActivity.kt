package com.pgdevhouse.devicepulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pgdevhouse.devicepulse.ui.theme.DevicePulseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DevicePulseTheme {
                DevicePulseApp()
            }
        }
    }
}