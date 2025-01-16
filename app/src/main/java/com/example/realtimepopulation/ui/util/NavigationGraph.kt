package com.example.realtimepopulation.ui.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.realtimepopulation.ui.main.MainScreen
import com.example.realtimepopulation.ui.map.MapScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MainScreen()
        }
        composable(Screen.Map.route) {
            MapScreen()
        }
    }
}