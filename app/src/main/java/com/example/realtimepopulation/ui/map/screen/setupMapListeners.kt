package com.example.realtimepopulation.ui.map.screen

import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.ui.map.viewmodel.MapViewModel
import com.naver.maps.map.NaverMap

fun setupMapListeners(
    naverMap: NaverMap,
    mapViewModel: MapViewModel,
    seoulLocationData: List<LocationData>,
) {
    with(naverMap) {
        setOnMapClickListener { _, _ ->
            mapViewModel.updateSelectedMarker(null, null)
        }
        addOnCameraChangeListener { _, _ ->
            mapViewModel.updateMapMovingState(true)
        }
        addOnCameraIdleListener {
            mapViewModel.updateMapMovingState(false)
            mapViewModel.updateCardPosition(naverMap, seoulLocationData)
        }
    }
}