package com.example.realtimepopulation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.realtimepopulation.ui.main.MainScreen
import com.example.realtimepopulation.ui.main.MainViewModel
import com.example.realtimepopulation.ui.theme.RealtimePopulationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealtimePopulationTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}