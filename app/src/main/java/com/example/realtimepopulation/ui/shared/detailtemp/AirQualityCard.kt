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
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun AirQualityCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Card(
        modifier = modifier
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = AppColors.White),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        shape = RoundedCornerShape(AppSpacing.Small)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppSpacing.Small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                fontSize = AppFontSizes.LabelSmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                maxLines = 2,
                lineHeight = AppFontSizes.LabelMedium
            )
            Spacer(modifier = Modifier.height(AppSpacing.Small))
            Text(
                text = "${value}ppm",
                fontSize = AppFontSizes.BodySmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00C73C),
                maxLines = 1
            )
            Text(
                text = "좋음",
                fontSize = AppFontSizes.LabelSmall,
                color = Color(0xFF00C73C)
            )
        }
    }
}