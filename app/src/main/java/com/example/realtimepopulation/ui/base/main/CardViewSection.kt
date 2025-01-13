package com.example.realtimepopulation.ui.base.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.data.main.MapData
import com.example.realtimepopulation.ui.base.CustomCardView

@Composable
fun CardViewSection(loc: List<LocationData>, popData: List<MapData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp, Alignment.CenterHorizontally
        )
    ) {
        loc.forEach { loc ->
            CustomCardView(loc, popData)
        }
    }
}