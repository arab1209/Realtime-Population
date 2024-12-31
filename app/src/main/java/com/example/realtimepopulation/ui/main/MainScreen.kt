package com.example.realtimepopulation.ui.main

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.realtimepopulation.data.main.LocationData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapType
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val (seoulLocationData, viewModel) = getSeoulAreaNames(context)

    /*val navController = rememberNavController()
    NavGraph(navController)*/

    NaverMap(
        modifier = Modifier.fillMaxSize()
    ) {
        seoulLocationData?.forEach {
            Marker(
                state = MarkerState(position = LatLng(it.lat, it.long)),
                captionText = it.areaName
            )
        }
    }
}

@Composable
fun getSeoulAreaNames(context: Context): Pair<List<LocationData>?, MainViewModel> {
    val viewModel: MainViewModel = viewModel()
    viewModel.readSeoulAreasFromExcel(context)
    val seoulLocationData by viewModel.seoulLocationData.observeAsState()
    return Pair(seoulLocationData, viewModel)
}