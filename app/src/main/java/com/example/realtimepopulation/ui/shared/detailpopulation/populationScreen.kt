package com.example.realtimepopulation.ui.shared.detailpopulation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.shared.DetailScreenChart
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun populationScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val detailScreenData = viewModel.detailScreenData.observeAsState()
    val dtViewModel: DetailScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(10.dp),
                title = {
                    Text(
                        text = "${detailScreenData.value!!.areaName} 실시간 인구 현황",
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
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

                // 이미지 및 메시지 출력
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    AsyncImage(
                        model = dtViewModel.analyzeImgUrl(detailScreenData.value!!.congestionLevel)
                            .trim(),
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

                    // 공지 박스
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 30.dp, vertical = 10.dp)
                            .background(Color(0xfffcfaf4))
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            fontSize = 12.sp,
                            text = "※ 혼잡도는 통신사의 실시간 인구 데이터를 분석하여 가공한 것으로, 실제 현장과는 차이가 있을 수 있음을 알려드립니다."
                        )
                    }

                    Divider(
                        color = Color(0xffe7e8ee), thickness = 5.dp
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp) // 공통 패딩 적용
                    ) {
                        populationTitleBox("실시간 인구 및 혼잡도 추이 전망")

                        DetailScreenChart(
                            viewModel,
                            detailScreenData.value?.forecasts,
                            viewModel.chartMinMax.value,
                            viewModel.chartData.value
                        )

                        populationSubTitleBox("인구 밀집 시간대")
                        populationSubTitleBox("인구 분산 시간대")
                    }
                }
            }
        }
    }
}
