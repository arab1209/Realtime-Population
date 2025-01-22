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
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.ui.base.CustomCardView

@Composable
fun MapCardView(
    isVisible: Boolean,
    selectedMarker: String?,
    cardPosition: PointF?,
    seoulLocationData: List<LocationData>,
) {
    if (!isVisible || selectedMarker == null || cardPosition == null) return

    val location = seoulLocationData.find { it.areaName == selectedMarker } ?: return

    CustomCardView(loc = location,
        modifier = Modifier
            .offset(x = with(LocalDensity.current) { cardPosition.x.toDp() - 100.dp },
                y = with(LocalDensity.current) { cardPosition.y.toDp() - 120.dp })
            .width(300.dp)
            .height(250.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) { })
}