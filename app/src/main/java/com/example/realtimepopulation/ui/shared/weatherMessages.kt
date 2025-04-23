package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.domain.model.main.WeatherStts

@Composable
fun weatherMessages(weather: WeatherStts) {
    listOfNotNull(weather.airMsg, weather.pcpMsg, weather.uvMsg).forEach { msg ->
        Text(
            text = msg,
            color = Color(0xff626262),
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}