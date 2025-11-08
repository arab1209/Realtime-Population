package com.example.realtimepopulation.ui.map.screen

import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.ui.theme.MapDimens
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons

/**
 * 지도에 서울 지역별 마커들을 생성하고 설정합니다.
 *
 * 각 마커는 지역의 위치, 이름, 혼잡도에 따른 색상을 표시하며,
 * 클릭 시 해당 지역의 상세 정보를 보여주는 카드를 표시합니다.
 *
 * @param naverMap 마커를 추가할 네이버 지도 인스턴스
 * @param markers 지도에 표시할 서울 지역 위치 데이터 목록
 * @param densityValue 화면 밀도 값 (마커 크기를 dp에서 픽셀로 변환)
 * @param onMarkerClick 마커 클릭 시 실행될 콜백 함수
 * @param getMarkerColor 각 지역의 혼잡도에 따른 마커 색상을 반환하는 함수
 */

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
            width = (MapDimens.CardMarkerSizeX * densityValue).toInt()
            height = (MapDimens.CardMarkerSizeY * densityValue).toInt()
            setOnClickListener {
                onMarkerClick(area)
                true
            }
            map = naverMap
        }
    }
}