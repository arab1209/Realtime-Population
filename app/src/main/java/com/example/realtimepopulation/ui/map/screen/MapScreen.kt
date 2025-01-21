package com.example.realtimepopulation.ui.map.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.util.MarkerIcons

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(vm: MainViewModel = hiltViewModel()) {
    val seoulLocationData = vm.seoulLocationData.collectAsState()
    val populationData = vm.populationData.collectAsState()

    NaverMap(modifier = Modifier.fillMaxSize()) {
        seoulLocationData.value.forEach { area ->
            val temp = populationData.value.find { it.seoulRtd.areaName == area.areaName }?.seoulRtd?.areaCongestLvl
            Marker(
                state = MarkerState(position = LatLng(area.lat, area.long)),
                captionText = area.areaName,
                width = 25.dp ,
                height = 35.dp,
                iconTintColor = if(temp != null ) {
                    vm.calcAreaColor(temp)
                } else Color.Transparent,
                icon = MarkerIcons.BLACK
            )
        }
    }
}