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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.CustomCardView
import com.example.realtimepopulation.ui.theme.MapDimens

@Composable
fun MapCardView(
    mainViewModel: MainViewModel = hiltViewModel(),
    isVisible: Boolean,
    selectedMarker: String?,
    cardPosition: PointF?,
    seoulLocationData: List<LocationData>,
    navController: NavController,
) {
    if (!isVisible || selectedMarker == null || cardPosition == null) return

    val location = seoulLocationData.find { it.areaName == selectedMarker } ?: return

    CustomCardView(
        mainViewModel,
        loc = location,
        modifier = Modifier
            .offset(x = with(LocalDensity.current) { cardPosition.x.toDp() - MapDimens.CardPositionX },
                y = with(LocalDensity.current) { cardPosition.y.toDp() - MapDimens.CardPositionY })
            .width(MapDimens.CardSizeX)
            .height(MapDimens.CardSizeY)
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) { },
        navController
    )
}