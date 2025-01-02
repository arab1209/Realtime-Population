package com.example.realtimepopulation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.realtimepopulation.ui.main.MainScreen
import com.example.realtimepopulation.ui.theme.RealtimePopulationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealtimePopulationTheme {
                MainScreen()
            }
        }
    }
}