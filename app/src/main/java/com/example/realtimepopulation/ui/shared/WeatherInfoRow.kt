package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.domain.model.main.WeatherStts

@Composable
fun WeatherInfoRow(weather: WeatherStts, items: List<Pair<String, Color>>) {
    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)) {
        items.forEachIndexed { index, (text, color) ->
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                Text(text = text, fontSize = 12.sp, color = Color(0xff626262), fontWeight = FontWeight.Bold)
            }
            if (index < items.lastIndex) WeatherBoxDivider()
        }
    }
}