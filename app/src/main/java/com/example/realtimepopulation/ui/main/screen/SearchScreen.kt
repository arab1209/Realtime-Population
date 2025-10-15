package com.example.realtimepopulation.ui.main.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun SearchScreen (viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    SearchBarSection(viewModel, navController)
}