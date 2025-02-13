package com.example.realtimepopulation.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.realtimepopulation.R
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
    }
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 15.dp)
            ) {
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "실시간 인구",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff4c65a7),
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_right_button),
                            contentDescription = "img",
                            modifier = Modifier
                                .width(35.dp)
                                .height(35.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .background(Color(0xfff7f7f7))
                    ) {
                        Text(
                            "인구혼잡도", fontSize = 16.sp, textDecoration = TextDecoration.Underline
                        )
                        Text(
                            "가 ", fontSize = 16.sp
                        )
                        Text(
                            detailScreenData.value!!.congestionLevel,
                            color = viewModel.calcAreaColor(detailScreenData.value!!.congestionLevel),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Text(
                            " 입니다", fontSize = 16.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Column() {
                            Text(
                                text = "현재 실시간 인구 : ${detailScreenData.value?.minCount} ~ ${detailScreenData.value?.maxCount}명",
                                fontSize = 16.sp,
                                color = Color(0xff798fd2),
                                fontWeight = FontWeight.Bold
                            )
                            congestMessageList.value.forEach {
                                Text(
                                    text = it,
                                    color = Color(0xff626262),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Chart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp), chart = columnChart(
                                columns = detailScreenData.value!!.forecasts.map { dataItem ->
                                    lineComponent(
                                        color = viewModel.calcAreaColor(dataItem.congestionLevel),
                                        thickness = 15.dp,
                                        shape = Shapes.cutCornerShape(
                                            topRightPercent = 20,
                                            topLeftPercent = 20
                                        )
                                    )
                                },
                                spacing = 30.dp,
                                dataLabel = TextComponent.Builder().build(),
                                axisValuesOverrider = AxisValuesOverrider.fixed(
                                    minY = 0f, maxY = maxData.value.toFloat()
                                )
                            ), chartModelProducer = ChartEntryModelProducer(chartData.value.map {
                                entryOf(
                                    it.index, it.xData
                                )
                            }), startAxis = rememberStartAxis(
                                itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 6)
                            ), bottomAxis = rememberBottomAxis(valueFormatter = { value, _ ->
                                (chartData.value.map {
                                    it.time
                                }[value.toInt()])
                            }), chartScrollState = rememberChartScrollState()
                        )
                    }
                }
            }
        }
    }
}