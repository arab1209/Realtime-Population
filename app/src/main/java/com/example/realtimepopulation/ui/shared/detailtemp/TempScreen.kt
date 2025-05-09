package com.example.realtimepopulation.ui.shared.detailtemp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.detailpopulation.PopulationTitleBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TempScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val weatherSttsData = viewModel.weatherSttsData.observeAsState()

    LaunchedEffect(weatherSttsData) {
        Log.d("test", weatherSttsData.toString())
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
                modifier = Modifier.fillMaxWidth(),
            ) {
                PopulationTitleBox("실시간 날씨 현황")

                // 온도 정보 행
                CenterDividerRow(
                    leftContent = {
                        TempInfoItem(
                            iconUrl = "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/icon_temperature.png",
                            text = "${weatherSttsData.value!!.cityData.weatherStts.temp}°C",
                            fontSize = 22.sp,
                            isBold = true,
                            color = Color(0xFF4169E1)
                        )
                    },
                    rightContent = {
                        Text(
                            text = "체감 ${weatherSttsData.value!!.cityData.weatherStts.sensibleTemp}°C",
                            fontSize = 22.sp
                        )
                    },
                    dividerHeight = 24.dp
                )

                // 습도와 바람 정보 행
                CenterDividerRow(
                    leftContent = {
                        TempInfoItem(
                            iconUrl = "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/icon_moisture.png",
                            text = "습도 ${weatherSttsData.value!!.cityData.weatherStts.humidity}%"
                        )
                    },
                    rightContent = {
                        TempInfoItem(
                            iconUrl = "https://data.seoul.go.kr/SeoulRtd/images/icon/weather/icon_wind.png",
                            text = "바람 ${weatherSttsData.value!!.cityData.weatherStts.windSpeed}m/s"
                        )
                    },
                    dividerHeight = 24.dp
                )

                // 최저/최고 기온과 일출/일몰 정보 행
                CenterDividerRow(
                    leftContent = {
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
                    },
                    rightContent = {
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
                    },
                    dividerHeight = 40.dp
                )
            }
        }
    }
}