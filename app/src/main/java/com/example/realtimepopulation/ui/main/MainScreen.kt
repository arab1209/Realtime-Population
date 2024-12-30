package com.example.realtimepopulation.ui.main

import android.util.Log
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    viewModel.readSeoulAreasFromExcel(LocalContext.current)
    val seoulAreaNames by viewModel.seoulAreaNames.observeAsState()

    LaunchedEffect(seoulAreaNames) {
        seoulAreaNames?.forEach {
            Log.d("seoul", it)
        }
    }

    Text(
        modifier = Modifier.systemBarsPadding(), text = "메인스크린"
    )
}