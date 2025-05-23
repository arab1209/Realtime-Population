package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@Composable
fun CongestionTimeBox(
    subTitleText: String,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    detailScreenData: MapData,
    flag: Int,
) {

    viewModel.analyzeCongestIconUrl(flag)
    viewModel.analyzeMaxMinPopulation(detailScreenData.forecasts)

    val congestIconUrl = when(flag) {
        0 -> viewModel.congestIconUrl0.collectAsState()
        1 -> viewModel.congestIconUrl1.collectAsState()
        else -> remember { mutableStateOf("") }
    }

    val minMaxTime = when(flag) {
        0 -> viewModel.maxPopulationHour.collectAsState()
        1 -> viewModel.minPopulationHour.collectAsState()
        else -> remember { mutableStateOf(Triple("00:00", "0", "0")) }
    }

    Box(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
        Column() {
            Text(
                text = subTitleText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    model = congestIconUrl.value, contentDescription = "시간 아이콘"
                )

                Column(modifier = Modifier.padding(start = 15.dp)) {
                    PopulationForecastText(
                        minMaxTime.value,
                        viewModel.getForecastText(flag)
                    )
                }
            }
        }
    }
}