package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.data.main.MapData
import com.example.realtimepopulation.ui.base.CustomCardView

@Composable
fun CardViewSection(selectChipData: List<LocationData>, index: Int, popData: List<MapData>) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, // 가운데 정렬
    ) {
        selectChipData.chunked(2)[index].forEach {
            CustomCardView(
                it, popData, modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .fillMaxHeight()
            )
        }

        if (selectChipData.chunked(2)[index].size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

