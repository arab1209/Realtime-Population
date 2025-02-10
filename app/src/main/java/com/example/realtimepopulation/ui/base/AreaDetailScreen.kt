package com.example.realtimepopulation.ui.base

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun AreaDetailScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val temp = viewModel.detailScreenData.observeAsState()
    Log.d("test", temp.value.toString())
}