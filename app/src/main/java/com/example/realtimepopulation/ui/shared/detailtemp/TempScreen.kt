package com.example.realtimepopulation.ui.shared.detailtemp

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.realtimepopulation.domain.model.detail.AirQualityData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.DetailScreenSubTitleBox
import com.example.realtimepopulation.ui.shared.detailpopulation.PopulationTitleBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TempScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val weatherSttsData = viewModel.weatherSttsData.observeAsState()
    val airMsg = viewModel.airMsg.collectAsState()

    LaunchedEffect(weatherSttsData) {
        Log.d("test", weatherSttsData.value.toString())
        viewModel.updateSafetyMessage(weatherSttsData.value!!.cityData.weatherStts.airMsg)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(10.dp),
            title = { Text(detailScreenData.value!!.areaName + " 날씨/환경 현황", fontSize = 20.sp) },
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
                        fontSize = 22.sp,
                        isBold = true,
                        color = Color(0xFF4169E1)
                    )
                }, rightContent = {
                    Text(
                        text = "체감 ${weatherSttsData.value!!.cityData.weatherStts.sensibleTemp}°C",
                        fontSize = 22.sp
                    )
                }, dividerHeight = 24.dp
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
                }, dividerHeight = 24.dp
                )

                // 최저/최고 기온과 일출/일몰 정보 행
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color.LightGray)
                        )
                        .padding(vertical = 5.dp)
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
                    }, dividerHeight = 40.dp
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
                        viewModel
                    )
                    TempRainUvInfoBox(
                        "자외선지수",
                        weatherSttsData.value!!.cityData.weatherStts.uvIndex,
                        "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/ico_weather_uv.png",
                        weatherSttsData.value!!.cityData.weatherStts.uvMsg,
                        modifier = Modifier.weight(1f),
                        viewModel
                    )
                }

                Divider(
                    color = Color(0xffe7e8ee),
                    thickness = 5.dp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                /**
                 *   대기오염현황 섹션
                 **/
                PopulationTitleBox("대기오염 현황")
                DetailScreenSubTitleBox(
                    "통합대기환경지수", viewModel, weatherSttsData.value!!.cityData.weatherStts.airIndex
                )
                Text(
                    text = airMsg.value,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
                AirMsgCenterDividerRow(leftContent = {
                    Text(
                        text = "미세먼지 ", fontSize = 16.sp
                    )
                    Text(
                        text = "${weatherSttsData.value!!.cityData.weatherStts.pm10}㎍/㎥ ${weatherSttsData.value!!.cityData.weatherStts.pm10Index}",
                        color = viewModel.calcAreaColor(weatherSttsData.value!!.cityData.weatherStts.pm10Index),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }, rightContent = {
                    Text(
                        text = "초미세먼지 ", fontSize = 16.sp
                    )
                    Text(
                        text = "${weatherSttsData.value!!.cityData.weatherStts.pm25}㎍/㎥ ${weatherSttsData.value!!.cityData.weatherStts.pm25Index}",
                        color = viewModel.calcAreaColor(weatherSttsData.value!!.cityData.weatherStts.pm25Index),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }, dividerHeight = 24.dp
                )/*Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    airQualityData.forEachIndexed { index, item ->
                        AirQualityItem(
                            name = item.name,
                            value = item.value,
                            status = item.status,
                            modifier = Modifier.weight(1f)
                        )

                        // 마지막 아이템이 아니면 구분선 추가
                        if (index < airQualityData.size - 1) {
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight()
                                    .padding(vertical = 8.dp)
                                    .background(Color.LightGray)
                            )
                        }
                    }
                    */
            }
        }
    }
}