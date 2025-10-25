package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.domain.model.main.WeatherForecast
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun WeatherForecastList(forecastData: List<WeatherForecast>) {
    val viewModel: DetailScreenViewModel = hiltViewModel()

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(top = AppSpacing.Medium)
        ) {
            listOf("시간", "온도", "강수량", "강수확률").forEachIndexed { index, label ->
                WeatherBox(label, viewModel.getFirstTabColor(index))
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppSpacing.Medium)
        ) {
            items(count = forecastData.size, key = { it }) { index ->
                WeatherForecastColumn(forecastData[index])
            }
        }
    }
}