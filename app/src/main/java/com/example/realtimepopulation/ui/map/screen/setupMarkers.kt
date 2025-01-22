package com.example.realtimepopulation.ui.map.screen

import com.example.realtimepopulation.data.main.LocationData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons

fun setupMarkers(
    naverMap: NaverMap,
    markers: List<LocationData>,
    densityValue: Float,
    onMarkerClick: (LocationData) -> Unit,
    getMarkerColor: (LocationData) -> Int,
) {
    markers.forEach { area ->
        Marker().apply {
            position = LatLng(area.lat, area.long)
            captionText = area.areaName
            iconTintColor = getMarkerColor(area)
            icon = MarkerIcons.BLACK
            width = (25 * densityValue).toInt()
            height = (35 * densityValue).toInt()
            setOnClickListener {
                onMarkerClick(area)
                true
            }
            map = naverMap
        }
    }
}