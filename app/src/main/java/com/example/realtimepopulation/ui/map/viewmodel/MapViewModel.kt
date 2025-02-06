package com.example.realtimepopulation.ui.map.viewmodel

import android.graphics.PointF
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.domain.model.main.LocationData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapViewModel : ViewModel() {
    private val _selectedMarker = MutableStateFlow<String?>(null)
    val selectedMarker = _selectedMarker.asStateFlow()

    private val _cardPosition = MutableStateFlow<PointF?>(null)
    val cardPosition = _cardPosition.asStateFlow()

    private val _isMapMoving = MutableStateFlow(false)
    val isMapMoving = _isMapMoving.asStateFlow()

    fun updateMapMovingState(isMoving: Boolean) {
        _isMapMoving.value = isMoving
    }

    fun updateSelectedMarker(areaName: String?, position: PointF?) {
        _selectedMarker.value = areaName
        _cardPosition.value = position
    }

    fun updateCardPosition(naverMap: NaverMap, seoulLocationData: List<LocationData>) {
        _selectedMarker.value?.let { areaName ->
            val area = seoulLocationData.firstOrNull { it.areaName == areaName }
            area?.let {
                val markerLatLng = LatLng(it.lat, it.long)
                _cardPosition.value = naverMap.projection.toScreenLocation(markerLatLng)
            }
        }
    }
}