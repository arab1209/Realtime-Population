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