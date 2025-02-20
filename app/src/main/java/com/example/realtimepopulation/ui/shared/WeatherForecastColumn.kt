package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.realtimepopulation.domain.model.main.WeatherForecast
import org.apache.poi.ss.formula.functions.Forecast

@Composable
fun WeatherForecastColumn(forecast: WeatherForecast) {
    Column {
        listOf(
            forecast.fcstDt,
            forecast.temp,
            forecast.precipitation,
            "데이터4",
            "데이터5"
        ).forEach { data ->
            WeatherBox(data)
        }
    }
}