package com.example.realtimepopulation.ui.shared.detailpopulation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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
import com.example.realtimepopulation.ui.shared.DetailScreenChart
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopulationScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val dtViewModel: DetailScreenViewModel = hiltViewModel()
    val congestImgUrl = dtViewModel.congestLevelImgUrl.collectAsState()
    val genderChartSection by dtViewModel.genderChartSection.collectAsState()
    val ageChartSection by dtViewModel.ageChartSection.collectAsState()
    val residentChartSection by dtViewModel.residentChartSection.collectAsState()

    dtViewModel.toDomainModel(detailScreenData.value!!)

    with(dtViewModel) {
        analyzeCongestImgUrl(detailScreenData.value!!.congestionLevel)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier.shadow(10.dp), title = {
            Text(
                text = "${detailScreenData.value!!.areaName} 실시간 인구 현황", fontSize = 20.sp
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(AppColors.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // 인구 혼잡도 표시
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AppSpacing.Medium),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("인구 혼잡도 ", fontWeight = FontWeight.Bold, fontSize = AppFontSizes.TitleLarge)
                    Text(
                        fontSize = AppFontSizes.BodyLarge,
                        text = detailScreenData.value?.congestionLevel ?: "",
                        color = viewModel.calcAreaColor(
                            detailScreenData.value?.congestionLevel ?: "",
                        ),
                        fontWeight = FontWeight.Bold
                    )
                }

                AsyncImage(
                    model = congestImgUrl.value,
                    contentDescription = detailScreenData.value!!.areaName,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                viewModel.congestMessage.value.forEach { string ->
                    Text(
                        fontSize = AppFontSizes.BodyMedium,
                        text = string,
                        modifier = Modifier.padding(horizontal = AppSpacing.ExtraExtraLarge, vertical = AppSpacing.Medium)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = AppSpacing.Medium, start= AppSpacing.ExtraExtraLarge, end = AppSpacing.ExtraExtraLarge)
                        .background(Color(0xfffcfaf4))
                ) {
                    Text(
                        modifier = Modifier.padding(AppSpacing.ExtraSmall),
                        fontSize = AppFontSizes.BodySmall,
                        text = "※ 혼잡도는 통신사의 실시간 인구 데이터를 분석하여 가공한 것으로, 실제 현장과는 차이가 있을 수 있음을 알려드립니다."
                    )
                }

                Divider(
                    color = Color(0xffe7e8ee), thickness = 5.dp,
                    modifier = Modifier.padding(top = AppSpacing.Medium)
                )

                PopulationTitleBox("실시간 인구 및 혼잡도 추이 전망")

                DetailScreenChart(
                    getAreaColor = viewModel::calcAreaColor,
                    detailScreenData.value?.forecasts,
                    viewModel.chartMinMax.value,
                    viewModel.chartData.value
                )

                CongestionTimeBox(
                    subTitleText = "인구 밀집 시간대",
                    congestIconUrl = dtViewModel.congestIconUrl0.collectAsState().value,
                    timeData = dtViewModel.maxPopulationHour.collectAsState().value,
                    forecastText = dtViewModel.getForecastText(0)
                )

                CongestionTimeBox(
                    subTitleText = "인구 분산 시간대",
                    congestIconUrl = dtViewModel.congestIconUrl1.collectAsState().value,
                    timeData = dtViewModel.minPopulationHour.collectAsState().value,
                    forecastText = dtViewModel.getForecastText(1)
                )


                Divider(
                    color = Color(0xffe7e8ee), thickness = 5.dp,
                    modifier = Modifier.padding(top = AppSpacing.Medium)
                )

                PopulationTitleBox("실시간 인구 구성 비율")

                PopulationDistributionChartRate(
                    genderChartSection = genderChartSection,
                    ageChartSection = ageChartSection,
                    residentChartSection = residentChartSection,
                    calculateChart = dtViewModel::calculateChart,  // 바로 사용
                    calculateSegmentDraw = dtViewModel::calculateSegmentDraw
                )
            }
        }
    }
}
