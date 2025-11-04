package com.example.realtimepopulation.ui.map.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.realtimepopulation.ui.util.Screen
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView

@Composable
fun MapScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val mapViewModel: MapViewModel = hiltViewModel()

    val density = LocalDensity.current.density
    val context = LocalContext.current

    val seoulLocationData by mainViewModel.seoulLocationData.collectAsState()
    val populationData by mainViewModel.populationData.collectAsState()
    val selectedMarker by mapViewModel.selectedMarker.collectAsState()
    val cardPosition by mapViewModel.cardPosition.collectAsState()
    val isMapMoving by mapViewModel.isMapMoving.collectAsState()
    val populationMapData by mainViewModel.populationDataMap.collectAsState()

    val mapView = remember { MapView(context) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                view.getMapAsync { naverMap ->
                    setupMapListeners(naverMap, mapViewModel, seoulLocationData)
                    setupMarkers(
                        naverMap = naverMap,
                        markers = seoulLocationData,
                        densityValue = density,
                        onMarkerClick = { area ->
                            mapViewModel.updateSelectedMarker(
                                area.areaName,
                                naverMap.projection.toScreenLocation(LatLng(area.lat, area.long))
                            )
                        },
                        getMarkerColor = { area ->
                            populationMapData[area.areaName]?.congestionLevel?.let {
                                mainViewModel.calcAreaColor(it).toArgb()
                            } ?: Color.Transparent.toArgb()
                        }
                    )
                }
            }
        )

        MapCardView(
            isVisible = !isMapMoving,
            selectedMarker = selectedMarker,
            cardPosition = cardPosition,
            seoulLocationData = seoulLocationData,
            populationDataMap = populationMapData,
            calcAreaColor = mainViewModel::calcAreaColor,
            onCardClick = { areaName ->
                mainViewModel.setDetailScreenData(populationData, areaName)
                navController.navigate(Screen.Detail.route)
            }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView.onDestroy()
        }
    }
}