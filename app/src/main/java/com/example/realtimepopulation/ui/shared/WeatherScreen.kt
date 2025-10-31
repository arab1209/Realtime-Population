package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.domain.model.main.WeatherForecast
import com.example.realtimepopulation.domain.model.main.WeatherSttsData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun WeatherScreen(
    weatherSttsData: WeatherSttsData?,
    calcAreaColor: (String) -> Color,
    calcTime: (String) -> String,
    getFirstTabColor: (Int) -> Modifier
) {
    weatherSttsData?.cityData?.weatherStts?.let { weather ->
        Column(modifier = Modifier.fillMaxWidth()) {
            // 날씨 메시지들
            listOfNotNull(weather.airMsg, weather.pcpMsg, weather.uvMsg).forEach { msg ->
                Text(
                    text = msg,
                    color = Color(0xff626262),
                    fontSize = AppFontSizes.BodySmall,
                    modifier = Modifier.padding(vertical = AppSpacing.ExtraSmall)
                )
            }

            WeatherDivider()

            // 첫 번째 정보 행: 온도, 체감온도, 자외선, 강수량
            WeatherInfoRow(
                items = listOf(
                    Triple("현재 ", "${weather.temp}\u2103", Color(0xff4c65a7)),
                    Triple("체감 ", "${weather.sensibleTemp}\u2103", Color.Unspecified),
                    Triple("자외선 지수 ", weather.uvIndex, calcAreaColor(weather.uvIndex)),
                    Triple("강수량 ", weather.precipitation, Color.Unspecified)
                )
            )

            WeatherDivider()

            // 두 번째 정보 행: 미세먼지
            WeatherInfoRow(
                items = listOf(
                    Triple(
                        "미세먼지 ",
                        "${weather.pm10Index}(${weather.pm10}㎍/㎡)",
                        calcAreaColor(weather.pm10Index)
                    ),
                    Triple(
                        "초미세먼지 ",
                        "${weather.pm25Index}(${weather.pm25}㎍/㎡)",
                        calcAreaColor(weather.pm25Index)
                    )
                )
            )

            WeatherDivider()

            // 시간별 예보
            WeatherForecastList(
                forecastData = weather.forecast,
                calcTime = calcTime,
                getFirstTabColor = getFirstTabColor
            )
        }
    }
}

@Composable
private fun WeatherDivider() {
    Divider(
        color = Color(0xffe7e8ee),
        thickness = AppSpacing.ExtraSmall
    )
}

@Composable
private fun WeatherInfoRow(items: List<Triple<String, String, Color>>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, (label, value, color) ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = label,
                    fontSize = AppFontSizes.BodySmall,
                    color = Color.Black
                )
                Text(
                    text = value,
                    fontSize = AppFontSizes.BodySmall,
                    color = color,
                    fontWeight = FontWeight.Bold
                )
            }

            // 마지막 항목이 아니면 구분선
            if (index < items.lastIndex) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(Color(0xffe7e8ee))
                )
            }
        }
    }
}

@Composable
private fun WeatherForecastList(
    forecastData: List<WeatherForecast>,
    calcTime: (String) -> String,
    getFirstTabColor: (Int) -> Modifier
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // 왼쪽 라벨 컬럼
        Column(modifier = Modifier.padding(top = AppSpacing.Medium)) {
            listOf("시간", "온도", "강수량", "강수확률").forEachIndexed { index, label ->
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .border(1.dp, Color(0xffe7e8ee))
                        .padding(4.dp)
                        .then(getFirstTabColor(index)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = AppFontSizes.BodySmall,
                        color = Color.Black
                    )
                }
            }
        }

        // 오른쪽 예보 데이터
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppSpacing.Medium)
        ) {
            items(count = forecastData.size, key = { it }) { index ->
                val forecast = forecastData[index]

                Column {
                    listOf(
                        calcTime(forecast.fcstDt),
                        forecast.temp,
                        forecast.precipitation,
                        forecast.rainChance + "%",
                    ).forEachIndexed { rowIndex, data ->
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .border(1.dp, Color(0xffe7e8ee))
                                .padding(4.dp)
                                .then(getFirstTabColor(rowIndex)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = data,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = AppFontSizes.BodySmall,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}