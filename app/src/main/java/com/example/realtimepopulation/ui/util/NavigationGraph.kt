package com.example.realtimepopulation.ui.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.realtimepopulation.ui.main.screen.MainScreen
import com.example.realtimepopulation.ui.main.screen.SearchScreen
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.map.screen.MapScreen
import com.example.realtimepopulation.ui.shared.AreaDetailScreen
import com.example.realtimepopulation.ui.shared.detailpopulation.PopulationScreen
import com.example.realtimepopulation.ui.shared.detailtemp.TempScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MainScreen(viewModel, navController)
        }
        composable(Screen.Map.route) {
            MapScreen(viewModel, navController)
        }
        composable(Screen.Detail.route) {
            AreaDetailScreen(viewModel, navController)
        }
        composable(Screen.DetailTemp.route) {
            TempScreen(viewModel, navController)
        }
        composable(Screen.DetailPopulation.route) {
            PopulationScreen(viewModel, navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(viewModel, navController)
        }
    }
}