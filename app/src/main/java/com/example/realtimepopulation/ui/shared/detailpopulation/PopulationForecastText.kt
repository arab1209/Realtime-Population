package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.realtimepopulation.domain.model.main.PopulationForecastTextData

@Composable
fun PopulationForecastText(
    minMaxTime: Triple<String, String, String>,
    forecastText: PopulationForecastTextData
) {
    val (time, _, afterHour) = minMaxTime

    Column {
        Row {
            Text(text = "$time(${afterHour}시간 후)", color = Color(0xff5568a4), fontWeight = FontWeight.Bold)
            Text(text = "에 인구가 제일 ${forecastText.peakText}")
        }
        Text(text = "혼잡도도 가장 ${forecastText.congestionText} 것으로 예상돼요")
        Row {
            Text(text = "혼잡정도는 ")
            Text(text = forecastText.levelText, color = forecastText.color, fontWeight = FontWeight.Bold)
            Text(text = " ${forecastText.levelEndText}")
        }
    }
}
