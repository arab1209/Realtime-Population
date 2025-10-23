package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.domain.model.main.PopulationForecastTextData
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes

@Composable
fun PopulationForecastText(
    minMaxTime: Triple<String, String, String>, forecastText: PopulationForecastTextData
) {
    val (time, _, afterHour) = minMaxTime

    Column {
        Row {
            Text(
                text = "$time(${afterHour}시간 후)",
                color = AppColors.ForecastColor,
                fontWeight = FontWeight.Bold,
                fontSize = AppFontSizes.BodySmall
            )
            Text(text = "에 인구가 제일 ${forecastText.peakText}", fontSize = AppFontSizes.BodySmall)
        }
        Text(
            text = "혼잡도도 가장 ${forecastText.congestionText} 것으로 예상돼요",
            fontSize = AppFontSizes.BodySmall
        )
        Row {
            Text(text = "혼잡정도는 ", fontSize = AppFontSizes.BodySmall)
            Text(
                text = forecastText.levelText,
                color = forecastText.color,
                fontWeight = FontWeight.Bold,
                fontSize = AppFontSizes.BodySmall
            )
            Text(text = " ${forecastText.levelEndText}", fontSize = AppFontSizes.BodySmall)
        }
    }
}
