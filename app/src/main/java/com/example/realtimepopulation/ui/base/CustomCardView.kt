package com.example.realtimepopulation.ui.base

import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.data.main.MapData

@Composable
fun CustomCardView(loc: LocationData, popData: List<MapData>, modifier: Modifier) {
    Box(modifier = modifier.padding(horizontal = 13.dp, vertical = 15.dp)) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(6.dp), colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF3F8FE)
            )
        ) {

            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(6.dp)),
                model = ImageRequest.Builder(LocalContext.current).data(loc.imgURL).build(),
                contentDescription = loc.areaName,
            )

            Text(
                text = loc.category,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = SpaceBetween
            ) {
                Text(
                    text = loc.areaName,
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                )
                Text(
                    text = popData.find { it.seoulRtd.areaName == loc.areaName }?.seoulRtd?.areaCongestLvl
                        ?: "",
                    fontSize = 10.sp,
                    lineHeight = 8.sp,
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}