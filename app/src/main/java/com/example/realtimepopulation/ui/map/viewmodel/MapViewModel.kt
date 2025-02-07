package com.example.realtimepopulation.ui.map.viewmodel

import android.graphics.PointF
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.usecase.map.UpdateCardPositionUseCase
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val updateCardPositionUseCase: UpdateCardPositionUseCase,
) : ViewModel() {
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
        updateCardPositionUseCase(naverMap, seoulLocationData, _selectedMarker, _cardPosition)
    }
}