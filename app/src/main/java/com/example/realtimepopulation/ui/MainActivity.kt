package com.example.realtimepopulation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.realtimepopulation.ui.theme.RealtimePopulationTheme
import com.example.realtimepopulation.ui.util.BottomNavigationBar
import com.example.realtimepopulation.ui.util.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            RealtimePopulationTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) },
                    content = {
                        Box(Modifier.padding(it)) {
                            NavigationGraph(navController = navController)
                        }
                    }
                )
            }
        }
    }
}