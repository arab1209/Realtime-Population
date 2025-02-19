package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.ui.shared.CustomCardView
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun CardViewSection(selectChipData: List<LocationData>, index: Int, navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, // 가운데 정렬
    ) {
        selectChipData.chunked(2)[index].forEach {
            CustomCardView(mainViewModel,
                it, modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .fillMaxHeight(),
                navController,
            )
        }

        if (selectChipData.chunked(2)[index].size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

