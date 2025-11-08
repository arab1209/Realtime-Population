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

/**
 * 인구 밀집/분산 시간대의 예측 정보를 텍스트로 표시하는 컴포넌트
 *
 * 특정 시간대의 인구 변화 예측과 혼잡도 정보를
 * 사용자가 이해하기 쉬운 자연어 형태로 제공합니다.
 *
 * 예시 출력:
 * "14:00(2시간 후)에 인구가 제일 많을 것으로 예상돼요
 * 혼잡도도 가장 높을 것으로 예상돼요
 * 혼잡정도는 붐빔 수준일 것 같아요"
 *
 * @param minMaxTime 시간 정보 Triple (시각, 미사용, 몇 시간 후)
 *                   예: Triple("14:00", "", "2")
 * @param forecastText 예측 텍스트 데이터 (인구 상태, 혼잡도, 색상 정보 포함)
 */

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
