package com.example.realtimepopulation.ui.map.screen

import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.ui.map.viewmodel.MapViewModel
import com.naver.maps.map.NaverMap

/**
 * 네이버 지도의 이벤트 리스너들을 설정합니다.
 *
 * 지도 클릭, 카메라 이동, 카메라 정지 이벤트를 처리하여
 * 마커 선택 상태와 카드 위치를 동적으로 업데이트합니다.
 *
 * @param naverMap 네이버 지도 인스턴스
 * @param mapViewModel 지도 UI 상태를 관리하는 ViewModel
 * @param seoulLocationData 서울 지역 위치 데이터 목록 (카드 위치 계산에 사용)
 */

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