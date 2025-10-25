package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.domain.model.main.WeatherStts
import com.example.realtimepopulation.ui.theme.AppFontSizes

@Composable
fun WeatherInfoRow(weather: WeatherStts, items: List<Triple<String, String, Color>>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, (text, text2, color) ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = text, fontSize = AppFontSizes.BodySmall, color = Color.Black
                )
                Text(
                    text = text2, fontSize = AppFontSizes.BodySmall, color = color, fontWeight = FontWeight.Bold
                )
            }
            if (index < items.lastIndex) WeatherBoxDivider()
        }
    }
}