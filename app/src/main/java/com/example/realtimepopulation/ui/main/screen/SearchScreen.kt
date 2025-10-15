package com.example.realtimepopulation.ui.main.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun SearchScreen (viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val savedSearchQuery by viewModel.savedSearchQuery.collectAsState()
    val searchList by viewModel.searchList.collectAsState()

    if(savedSearchQuery.isNotBlank()) {
        viewModel.searchLocationData(savedSearchQuery)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBarSection(viewModel, navController)
        searchList.forEach {
            Text(
                text = it.areaName
            )
        }

    }
}