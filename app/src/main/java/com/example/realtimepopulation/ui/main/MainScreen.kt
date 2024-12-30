package com.example.realtimepopulation.ui.main

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapType
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    /*val navController = rememberNavController()
    NavGraph(navController)*/
    val (seoulAreaNames, viewModel) = getSeoulAreaNames(context)

    NaverMap(
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun getSeoulAreaNames(context: Context): Pair<List<String>?, MainViewModel> {
    val viewModel: MainViewModel = viewModel()
    viewModel.readSeoulAreasFromExcel(context)
    val seoulAreaNames by viewModel.seoulAreaNames.observeAsState()
    return Pair(seoulAreaNames, viewModel)
}