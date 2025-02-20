package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.domain.model.main.WeatherSttsData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel

@Composable
fun WeatherScreen (weatherSttsData: WeatherSttsData?, viewModel: MainViewModel= hiltViewModel()) {
    weatherSttsData?.cityData?.weatherStts?.let { weather ->
        Column(modifier = Modifier.fillMaxWidth()) {
            weatherMessages(weather)
            WeatherDivider(thickness = 3.dp)
            WeatherInfoRow(
                weather = weather,
                items = listOf(
                    "현재 ${weather.temp}\u2103" to Color(0xff4c65a7),
                    "체감 ${weather.sensibleTemp}\u2103" to Color.Unspecified,
                    "자외선 지수 ${weather.uvIndex}" to viewModel.calcAreaColor(weather.uvIndex),
                    "강수량 ${weather.precipitation}" to Color.Unspecified
                )
            )
            WeatherDivider(thickness = 2.dp)
            WeatherInfoRow(
                weather = weather,
                items = listOf(
                    "미세먼지 ${weather.pm10Index}(${weather.pm10}㎍/㎡)" to viewModel.calcAreaColor(weather.pm10Index),
                    "초미세먼지 ${weather.pm25Index}(${weather.pm25}㎍/㎡)" to viewModel.calcAreaColor(weather.pm25Index)
                )
            )
            WeatherDivider(thickness = 3.dp)
            WeatherForecastList(weather.forecast)
        }
    }
}