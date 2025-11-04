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