package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.CustomCardView
import com.example.realtimepopulation.ui.theme.CardViewDimens

/**
 * 2열 그리드 카드 뷰 섹션
 *
 * 지역별 인구 현황을 카드 형태로 표시합니다.
 * 한 Row에 최대 2개의 카드를 배치하며, 1개만 있을 경우 나머지 공간은 Spacer로 채웁니다.
 *
 * @param locationDataPair 한 행에 표시할 지역 데이터 리스트 (최대 2개)
 * @param populationDataMap 지역명을 키로 하는 인구 데이터 맵
 * @param calcAreaColor 혼잡도에 따른 색상 계산 함수
 * @param onCardClick 카드 클릭 시 호출되는 콜백 (지역명 전달)
 */

@Composable
fun CardViewSection(
    locationDataPair: List<LocationData>,
    populationDataMap: Map<String, MapData>,
    calcAreaColor: (String) -> Color,
    onCardClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        locationDataPair.forEach { locationData ->
            val mapData = populationDataMap[locationData.areaName]

            CustomCardView(
                locationData = locationData,
                congestionLevel = mapData?.congestionLevel,
                congestionColor = mapData?.congestionLevel?.let { calcAreaColor(it) } ?: Color.Transparent,
                modifier = Modifier
                    .weight(CardViewDimens.Weight)
                    .aspectRatio(CardViewDimens.AspectRatio)
                    .fillMaxHeight(),
                onCardClick = { onCardClick(locationData.areaName) }
            )
        }

        if (locationDataPair.size == 1) {
            Spacer(modifier = Modifier.weight(CardViewDimens.Weight))
        }
    }
}