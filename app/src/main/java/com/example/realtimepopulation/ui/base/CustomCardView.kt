package com.example.realtimepopulation.ui.base

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.util.Screen

@Composable
fun CustomCardView(viewModel: MainViewModel = hiltViewModel(), loc: LocationData, modifier: Modifier, navController: NavController) {
    val populationData = viewModel.populationData.collectAsState()
    val temp = populationData.value.find { it.areaName == loc.areaName }


    Box(modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp)) { // 카드뷰 큰 영역
        Card(
            elevation = CardDefaults.elevatedCardElevation(6.dp), colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF3F8FE)
            )
        ) {
            AsyncImage( // 카드뷰 이미지
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .clickable {
                        viewModel.setDetailScreenData(populationData.value, loc.areaName)
                        navController.navigate(Screen.Detail.route)
                    },
                model = ImageRequest.Builder(LocalContext.current).data(loc.imgURL).build(),
                contentDescription = loc.areaName,
                contentScale = ContentScale.Crop,
            )

            Row( //카드뷰 텍스트 큰 영역
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(// 카드뷰 텍스트 왼쪽 영역
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = loc.category,
                        fontSize = 8.sp,
                        lineHeight = 8.sp,
                    )

                    Text(
                        text = loc.areaName,
                        fontSize = 8.sp,
                        lineHeight = 8.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 6.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Box( //카드뷰 텍스트 오른쪽 인구 분포도
                    modifier = Modifier
                        .padding(8.dp)
                        .size(10.dp)
                        .background(
                            if (temp != null) viewModel.calcAreaColor(temp.congestionLevel) else Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}