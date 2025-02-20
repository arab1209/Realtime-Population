package com.example.realtimepopulation.ui.shared

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaDetailScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()

    LaunchedEffect(detailScreenData) {
        viewModel.getCongestMessage(detailScreenData.value!!.congestionMessage)
        viewModel.getChartData(detailScreenData.value!!)
        viewModel.getWeatherStatus(detailScreenData.value!!.areaName)
    }

    val weatherSttsData = viewModel.weatherSttsData.observeAsState()
    val congestMessageList = viewModel.congestMessage.collectAsState()
    val maxData = viewModel.chartMinMax.collectAsState()
    val chartData = viewModel.chartData.collectAsState()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(10.dp),
            title = { Text(detailScreenData.value!!.areaName, fontSize = 20.sp) },
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
            LazyColumn() {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp)
                    ) {
                        Column() {
                            DetailScreenTitleBox("실시간 인구")
                            DetailScreenSubTitleBox("인구혼잡도", viewModel,
                                detailScreenData.value?.congestionLevel ?: ""
                            )
                            DetailScreenRtimeHumanCount(detailScreenData.value?.minCount, detailScreenData.value?.maxCount, congestMessageList.value)
                            DetailScreenChart(viewModel, detailScreenData.value?.forecasts, maxData.value, chartData.value)
                        }
                    }
                    Divider(
                        color = Color(0xffe7e8ee), thickness = 5.dp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp)
                    ) {
                        Column() {
                            DetailScreenTitleBox("날씨/환경 상황")
                            weatherSttsData.value?.cityData?.weatherStts?.let { weather ->
                                DetailScreenSubTitleBox("통합대기환경지수", viewModel, weather.airIndex)
                            }
                            WeatherScreen(weatherSttsData.value, viewModel)
                        }
                    }
                }
            }
        }
    }
}