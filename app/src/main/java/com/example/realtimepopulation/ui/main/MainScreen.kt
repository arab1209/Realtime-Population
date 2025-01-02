package com.example.realtimepopulation.ui.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.realtimepopulation.data.main.LocationData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val (seoulLocationData, viewModel) = getSeoulAreaNames(context)/*val navController = rememberNavController()
    NavGraph(navController)*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Black)
            ) { }
            Box(modifier = Modifier.fillMaxSize()) {
                NaverMap() {
                    seoulLocationData?.forEach {
                        Marker(
                            state = MarkerState(position = LatLng(it.lat, it.long)),
                            captionText = it.areaName,
                            width = 20.dp,
                            height = 30.dp
                        )
                    }
                }
            }
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