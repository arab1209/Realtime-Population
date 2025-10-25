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
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun WeatherScreen(weatherSttsData: WeatherSttsData?, viewModel: MainViewModel = hiltViewModel()) {
    weatherSttsData?.cityData?.weatherStts?.let { weather ->
        Column(modifier = Modifier.fillMaxWidth()) {
            weatherMessages(weather)
            WeatherDivider(thickness = AppSpacing.ExtraSmall)
            WeatherInfoRow(
                weather = weather, items = listOf(
                    Triple("현재 ", "${weather.temp}\u2103", Color(0xff4c65a7)),
                    Triple("체감 ", "${weather.sensibleTemp}\u2103", Color.Unspecified),
                    Triple("자외선 지수 ", weather.uvIndex, viewModel.calcAreaColor(weather.uvIndex)),
                    Triple("강수량 ", weather.precipitation, Color.Unspecified)
                )
            )
            WeatherDivider(thickness = AppSpacing.ExtraSmall)
            WeatherInfoRow(
                weather = weather, items = listOf(
                    Triple(
                        "미세먼지 ",
                        "${weather.pm10Index}(${weather.pm10}㎍/㎡)",
                        viewModel.calcAreaColor(weather.pm10Index)
                    ), Triple(
                        "초미세먼지 ",
                        "${weather.pm25Index}(${weather.pm25}㎍/㎡)",
                        viewModel.calcAreaColor(weather.pm25Index)
                    )
                )
            )
            WeatherDivider(thickness = AppSpacing.ExtraSmall)
            WeatherForecastList(weather.forecast)
        }
    }
}