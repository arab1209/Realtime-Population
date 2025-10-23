package com.example.realtimepopulation.ui.shared.detailtemp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun AirQualityItem(
    name: String,
    value: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(AppSpacing.Small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = AppFontSizes.BodyMedium,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(AppSpacing.Small))

        Text(
            text = value,
            fontSize = AppFontSizes.BodyLarge,
            color = Color(0xFFF0AD4E),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = status,
            fontSize = AppFontSizes.BodyMedium,
            color = Color(0xFFF0AD4E)
        )
    }
}