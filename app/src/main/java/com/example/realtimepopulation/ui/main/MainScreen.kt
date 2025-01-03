package com.example.realtimepopulation.ui.main

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.realtimepopulation.R
import com.example.realtimepopulation.data.main.LocationData
import com.naver.maps.map.compose.ExperimentalNaverMapApi

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val (seoulLocationData, viewModel) = getSeoulAreaNames(context)
    val searchQuery by viewModel.searchQuery.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {// 상단 나라, 지역 이름
                    Column() {
                        Text(
                            fontSize = 10.sp, color = Color.Black, text = "Korea"
                        )
                        Text(
                            fontSize = 32.sp, color = Color.Black, text = "Seoul"
                        )
                    }
                }
            }

            item {
                Box(  //서치바
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(color = Color(0xFFF3F8FE))

                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_main_search),
                            contentDescription = "ic_main_search",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(start = 12.dp, top = 10.dp, bottom = 10.dp)
                        )

                        BasicTextField(textStyle = TextStyle(
                            color = Color.Black, fontSize = 8.sp
                        ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // 실 입력 텍스트 UnderLine 없애기
                            singleLine = false,
                            cursorBrush = SolidColor(Color.Transparent),
                            value = searchQuery,
                            onValueChange = { viewModel.setQueryText(it) },
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .border(width = 0.dp, color = Color.Transparent),
                            decorationBox = { innerTextField ->
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        text = "Find things to do",
                                        color = Color(0xFFB8B8B8),
                                        fontSize = 8.sp
                                    )
                                }
                                innerTextField()
                            })
                    }
                }
            }
        }
    }/*val navController = rememberNavController()
    NavGraph(navController)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Black)
            ) { }
            Box(modifier = Modifier.fillMaxSize()) {
                NaverMap() {
                    seoulLocationData?.forEach {
                        Marker(
                            state = MarkerState(position = LatLng(it.lat, it.long)),
                            captionText = it.areaName,
                            width = 20.dp,
                            height = 30.dp
                        )
                    }
                }
            }
        }
    }*/
}

@Composable
fun getSeoulAreaNames(context: Context): Pair<List<LocationData>?, MainViewModel> {
    val viewModel: MainViewModel = viewModel()
    viewModel.readSeoulAreasFromExcel(context)
    val seoulLocationData by viewModel.seoulLocationData.collectAsState()
    return Pair(seoulLocationData, viewModel)
}