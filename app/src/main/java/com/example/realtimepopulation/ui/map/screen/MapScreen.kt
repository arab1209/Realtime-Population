package com.example.realtimepopulation.ui.map.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(mv: MainViewModel = hiltViewModel()) {
    val seoulLocationData = mv.seoulLocationData.collectAsState()

    NaverMap(modifier = Modifier.fillMaxSize()) {
        seoulLocationData.value.forEach {
            Marker(
                state = MarkerState(position = LatLng(it.lat, it.long)),
                captionText = it.areaName,
                width = 25.dp ,
                height = 35.dp
            )
        }
    }
}