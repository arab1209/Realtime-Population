package com.example.realtimepopulation.ui.base

import androidx.compose.foundation.background
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.data.main.MapData

@Composable
fun CustomCardView(loc: LocationData, popData: List<MapData>, modifier: Modifier) {
    val temp = popData.find { it.seoulRtd.areaName == loc.areaName }?.seoulRtd?.areaCongestLvl

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
                    .clip(RoundedCornerShape(6.dp)),
                model = ImageRequest.Builder(LocalContext.current).data(loc.imgURL).build(),
                contentDescription = loc.areaName,
                contentScale = ContentScale.Crop
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
                            if (temp != null) calcAreaColor(temp) else Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

private fun calcAreaColor(congestLvl: String): Color {
    return when (congestLvl) {
        "붐빔" -> Color(0xFFdd1e3d)
        "약간 붐빔" -> Color(0xFFff8041)
        "보통" -> Color(0xFFffb100)
        else -> Color(0xFF05d369)
    }
}