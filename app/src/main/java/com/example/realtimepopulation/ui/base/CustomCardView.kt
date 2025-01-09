package com.example.realtimepopulation.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.realtimepopulation.data.main.LocationData

@Composable
fun CustomCardView(loc: LocationData) {
    Column() {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF3F8FE)
            ), elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {

            AsyncImage(
                modifier = Modifier
                    .width(180.dp)
                    .padding(8.dp),
                model = ImageRequest.Builder(LocalContext.current).data(loc.imgURL).build(),
                contentDescription = loc.areaName
            )
            Text(
                text = loc.category,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = loc.areaName,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
            )

        }
    }
}