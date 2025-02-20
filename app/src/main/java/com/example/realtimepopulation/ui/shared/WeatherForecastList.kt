package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.realtimepopulation.domain.model.main.WeatherForecast
import org.apache.poi.ss.formula.functions.Forecast

@Composable
fun WeatherForecastList(forecastData: List<WeatherForecast>) {
    LazyRow(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
        items(count = forecastData.size, key = { it }) { index ->
            WeatherForecastColumn(forecastData[index])
        }
    }
}