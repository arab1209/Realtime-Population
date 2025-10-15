package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AirQualityCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Card(
        modifier = modifier
            .aspectRatio(1f), // 정사각형 비율
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                maxLines = 2,
                lineHeight = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${value}ppm",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00C73C),
                maxLines = 1
            )
            Text(
                text = "좋음",
                fontSize = 10.sp,
                color = Color(0xFF00C73C)
            )
        }
    }
}