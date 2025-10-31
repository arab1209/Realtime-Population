package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.DetailScreenSubTitleBox
import com.example.realtimepopulation.ui.shared.detailpopulation.PopulationTitleBox
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TempScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val weatherSttsData = viewModel.weatherSttsData.observeAsState()
    val airMsg = viewModel.airMsg.collectAsState()
    val airQualityData = viewModel.airQualityData.observeAsState()

    LaunchedEffect(weatherSttsData) {
        viewModel.updateSafetyMessage(weatherSttsData.value!!.cityData.weatherStts.airMsg)
    }

    LaunchedEffect(viewModel.regionName) {
        viewModel.getAirQualityData(viewModel.regionName.value)
    }

    viewModel.getRegionName()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(10.dp),
            title = {
                Text(
                    detailScreenData.value!!.areaName + " 날씨/환경 현황",
                    fontSize = AppFontSizes.TitleLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }
            })
    }) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                PopulationTitleBox("실시간 날씨 현황")

                // 온도 정보 행
                CenterDividerRow(leftContent = {
                    TempInfoItem(
                        iconUrl = "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/icon_temperature.png",
                        text = "${weatherSttsData.value!!.cityData.weatherStts.temp}°C",
                        fontSize = AppFontSizes.TitleLarge,
                        isBold = true,
                        color = Color(0xFF4169E1)
                    )
                }, rightContent = {
                    Text(
                        text = "체감 ${weatherSttsData.value!!.cityData.weatherStts.sensibleTemp}°C",
                        fontSize = AppFontSizes.TitleLarge
                    )
                }, dividerHeight = AppSpacing.ExtraLarge
                )

                // 습도와 바람 정보 행
                CenterDividerRow(leftContent = {
                    TempInfoItem(
                        iconUrl = "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/icon_moisture.png",
                        text = "습도 ${weatherSttsData.value!!.cityData.weatherStts.humidity}%"
                    )
                }, rightContent = {
                    TempInfoItem(
                        iconUrl = "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/icon_wind.png",
                        text = "바람 ${weatherSttsData.value!!.cityData.weatherStts.windSpeed}m/s"
                    )
                }, dividerHeight = AppSpacing.ExtraLarge
                )

                // 최저/최고 기온과 일출/일몰 정보 행
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color.LightGray)
                        )
                        .padding(vertical = AppSpacing.Small)
                ) {
                    CenterDividerRow(leftContent = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TempLabeledValue(
                                label = "최저기온",
                                value = weatherSttsData.value!!.cityData.weatherStts.minTemp
                            )
                            TempLabeledValue(
                                label = "최고기온",
                                value = weatherSttsData.value!!.cityData.weatherStts.maxTemp
                            )
                        }
                    }, rightContent = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TempLabeledValue(
                                label = "일출",
                                value = weatherSttsData.value!!.cityData.weatherStts.sunrise
                            )
                            TempLabeledValue(
                                label = "일몰",
                                value = weatherSttsData.value!!.cityData.weatherStts.sunset
                            )
                        }
                    }, dividerHeight = AppSpacing.ExtraExtraExtraLarge
                    )
                }
                /**
                 * 자외선 및 강수량 박스
                 **/
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TempRainUvInfoBox(
                        "강수량",
                        weatherSttsData.value!!.cityData.weatherStts.precipitation,
                        "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/ico_weather_rain.png",
                        weatherSttsData.value!!.cityData.weatherStts.pcpMsg,
                        modifier = Modifier.weight(1f),
                        getColor = viewModel::calcAreaColor
                    )
                    TempRainUvInfoBox(
                        "자외선지수",
                        weatherSttsData.value!!.cityData.weatherStts.uvIndex,
                        "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/ico_weather_uv.png",
                        weatherSttsData.value!!.cityData.weatherStts.uvMsg,
                        modifier = Modifier.weight(1f),
                        getColor = viewModel::calcAreaColor
                    )
                }

                Divider(
                    color = Color(0xffe7e8ee),
                    thickness = 5.dp,
                    modifier = Modifier.padding(top = AppSpacing.Medium)
                )

                PopulationTitleBox("대기오염 현황")
                DetailScreenSubTitleBox(
                    "통합대기환경지수",
                    getColor = viewModel::calcAreaColor,
                    weatherSttsData.value!!.cityData.weatherStts.airIndex
                )
                Text(
                    text = airMsg.value,
                    modifier = Modifier.padding(
                        horizontal = AppSpacing.MediumLarge,
                        vertical = AppSpacing.Medium
                    )
                )
                AirMsgCenterDividerRow(leftContent = {
                    Text(
                        text = "미세먼지 ", fontSize = AppFontSizes.BodyLarge
                    )
                    Text(
                        text = "${weatherSttsData.value!!.cityData.weatherStts.pm10}㎍/㎥ ${weatherSttsData.value!!.cityData.weatherStts.pm10Index}",
                        color = viewModel.calcAreaColor(weatherSttsData.value!!.cityData.weatherStts.pm10Index),
                        fontWeight = FontWeight.Bold,
                        fontSize = AppFontSizes.BodyLarge
                    )
                }, rightContent = {
                    Text(
                        text = "초미세먼지 ", fontSize = AppFontSizes.BodyLarge
                    )
                    Text(
                        text = "${weatherSttsData.value!!.cityData.weatherStts.pm25}㎍/㎥ ${weatherSttsData.value!!.cityData.weatherStts.pm25Index}",
                        color = viewModel.calcAreaColor(weatherSttsData.value!!.cityData.weatherStts.pm25Index),
                        fontWeight = FontWeight.Bold,
                        fontSize = AppFontSizes.BodyLarge
                    )
                }, dividerHeight = AppSpacing.ExtraLarge
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppSpacing.Large),
                    horizontalArrangement = Arrangement.spacedBy(AppSpacing.Small)
                ) {
                    AirQualityCard(
                        modifier = Modifier.weight(1f),
                        label = "오존농도",
                        value = airQualityData.value?.o3 ?: "0.000"
                    )
                    AirQualityCard(
                        modifier = Modifier.weight(1f),
                        label = "이산화질소",
                        value = airQualityData.value?.no2 ?: "0.000"
                    )
                    AirQualityCard(
                        modifier = Modifier.weight(1f),
                        label = "일산화탄소",
                        value = airQualityData.value?.c0 ?: "0.000"
                    )
                    AirQualityCard(
                        modifier = Modifier.weight(1f),
                        label = "아황산가스",
                        value = airQualityData.value?.so2 ?: "0.000"
                    )
                }
            }
        }
    }
}