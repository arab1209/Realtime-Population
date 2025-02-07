package com.example.realtimepopulation.domain.usecase.map

import android.graphics.PointF
import com.example.realtimepopulation.domain.model.main.LocationData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UpdateCardPositionUseCase @Inject constructor() {
    operator fun invoke(
        naverMap: NaverMap,
        seoulLocationData: List<LocationData>,
        selectedMarker: StateFlow<String?>,
        cardPosition: MutableStateFlow<PointF?>
    ) {
        selectedMarker.value?.let { areaName ->
            val area = seoulLocationData.firstOrNull { it.areaName == areaName }
            area?.let {
                val markerLatLng = LatLng(it.lat, it.long)
                cardPosition.value = naverMap.projection.toScreenLocation(markerLatLng)
            }
        }
    }
}