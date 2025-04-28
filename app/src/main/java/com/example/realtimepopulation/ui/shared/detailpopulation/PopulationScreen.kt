package com.example.realtimepopulation.ui.shared.detailpopulation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.DetailScreenChart
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopulationScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val dtViewModel: DetailScreenViewModel = hiltViewModel()
    val congestImgUrl = dtViewModel.congestLevelImgUrl.collectAsState()


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
                .background(Color.White)
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
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("인구 혼잡도 ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(
                        fontSize = 20.sp,
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
                        fontSize = 14.sp,
                        text = string,
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, start= 30.dp, end = 30.dp)
                        .background(Color(0xfffcfaf4))
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        fontSize = 12.sp,
                        text = "※ 혼잡도는 통신사의 실시간 인구 데이터를 분석하여 가공한 것으로, 실제 현장과는 차이가 있을 수 있음을 알려드립니다."
                    )
                }

                Divider(
                    color = Color(0xffe7e8ee), thickness = 5.dp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                PopulationTitleBox("실시간 인구 및 혼잡도 추이 전망")

                DetailScreenChart(
                    viewModel,
                    detailScreenData.value?.forecasts,
                    viewModel.chartMinMax.value,
                    viewModel.chartData.value
                )

                CongestionTimeBox("인구 밀집 시간대", dtViewModel, detailScreenData.value!!, 0)
                CongestionTimeBox("인구 분산 시간대", dtViewModel, detailScreenData.value!!, 1)

                Divider(
                    color = Color(0xffe7e8ee), thickness = 5.dp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                PopulationTitleBox("실시간 인구 구성 비율")

                PopulationDistributionChartRate(dtViewModel)
            }
        }
    }
}
