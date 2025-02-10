package com.example.realtimepopulation.ui.map.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.map.viewmodel.MapViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView

@Composable
fun MapScreen(mainViewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val mapViewModel: MapViewModel = hiltViewModel()

    val density = LocalDensity.current.density
    val context = LocalContext.current

    val seoulLocationData = mainViewModel.seoulLocationData.collectAsState()
    val populationData = mainViewModel.populationData.collectAsState()
    val selectedMarker = mapViewModel.selectedMarker.collectAsState()
    val cardPosition = mapViewModel.cardPosition.collectAsState()
    val isMapMoving = mapViewModel.isMapMoving.collectAsState()

    val mapView = remember { MapView(context) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { mapView }, modifier = Modifier.fillMaxSize(), update = { view ->
            view.getMapAsync { naverMap ->
                setupMapListeners(naverMap, mapViewModel, seoulLocationData.value)
                setupMarkers(naverMap = naverMap,
                    markers = mainViewModel.seoulLocationData.value,
                    densityValue = density,
                    onMarkerClick = { area ->
                        mapViewModel.updateSelectedMarker(
                            area.areaName,
                            naverMap.projection.toScreenLocation(LatLng(area.lat, area.long))
                        )
                    },
                    getMarkerColor = { area ->
                        populationData.value.find {
                            it.areaName == area.areaName
                        }?.congestionLevel?.let {
                            mainViewModel.calcAreaColor(it).toArgb()
                        } ?: Color.Transparent.toArgb()
                    })

            }

        })
    }

    MapCardView(
        mainViewModel,
        isVisible = !isMapMoving.value,
        selectedMarker = selectedMarker.value,
        cardPosition = cardPosition.value,
        seoulLocationData = seoulLocationData.value,
        navController
    )
}
