package com.example.realtimepopulation.ui.map.screen

import android.graphics.PointF
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.CustomCardView
import com.example.realtimepopulation.ui.theme.MapDimens

/**
 * 지도 위에 표시되는 지역 정보 카드 컴포넌트
 *
 * 선택된 마커의 위치에 따라 동적으로 위치가 결정되며,
 * 해당 지역의 혼잡도 정보를 시각적으로 표시합니다.
 *
 * @param isVisible 카드 표시 여부 (지도 이동 중에는 false)
 * @param selectedMarker 현재 선택된 마커의 지역명
 * @param cardPosition 카드를 표시할 화면 좌표 (픽셀 단위)
 * @param seoulLocationData 서울 지역 위치 데이터 목록
 * @param populationDataMap 지역명을 키로 하는 인구/혼잡도 데이터 맵
 * @param calcAreaColor 혼잡도 레벨에 따른 색상을 계산하는 함수
 * @param onCardClick 카드 클릭 시 실행될 콜백 (지역명 전달)
 */

@Composable
fun MapCardView(
    isVisible: Boolean,
    selectedMarker: String?,
    cardPosition: PointF?,
    seoulLocationData: List<LocationData>,
    populationDataMap: Map<String, MapData>,
    calcAreaColor: (String) -> Color,
    onCardClick: (String) -> Unit
) {
    if (!isVisible || selectedMarker == null || cardPosition == null) return

    val location = remember(selectedMarker, seoulLocationData) {
        seoulLocationData.find { it.areaName == selectedMarker }
    } ?: return

    val mapData = remember(selectedMarker, populationDataMap) {
        populationDataMap[selectedMarker]
    }

    val congestionColor = remember(mapData?.congestionLevel) {
        mapData?.congestionLevel?.let { calcAreaColor(it) } ?: Color.Transparent
    }

    val density = LocalDensity.current

    CustomCardView(
        locationData = location,
        congestionLevel = mapData?.congestionLevel,
        congestionColor = congestionColor,
        modifier = Modifier
            .offset(
                x = with(density) { cardPosition.x.toDp() - MapDimens.CardPositionX },
                y = with(density) { cardPosition.y.toDp() - MapDimens.CardPositionY }
            )
            .width(MapDimens.CardSizeX)
            .height(MapDimens.CardSizeY),
        onCardClick = { onCardClick(selectedMarker) }
    )
}