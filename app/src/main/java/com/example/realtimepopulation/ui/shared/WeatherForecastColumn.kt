package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.domain.model.main.WeatherForecast
import com.example.realtimepopulation.ui.main.viewmodel.DetailScreenViewModel

@Composable
fun WeatherForecastColumn(forecast: WeatherForecast) {
    val viewModel: DetailScreenViewModel = hiltViewModel()

    Column {
        listOf(
            viewModel.calcTime(forecast.fcstDt),
            forecast.temp,
            forecast.precipitation,
            forecast.rainChance + "%",
        ).forEachIndexed { index, data ->
            WeatherBox(data, viewModel.getFirstTabColor(index))
        }
    }
}