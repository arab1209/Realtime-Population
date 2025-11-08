package com.example.realtimepopulation.ui.shared.detailpopulation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realtimepopulation.domain.model.main.PopulationForecastTextData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

/**
 * 인구 밀집/분산 시간대 정보를 표시하는 박스 컴포넌트
 *
 * 특정 시간대의 인구 예측 정보를 아이콘과 함께 시각적으로 제공합니다.
 * 주로 "인구 밀집 시간대"와 "인구 분산 시간대" 두 가지 형태로 사용됩니다.
 *
 * @param subTitleText 섹션 제목 (예: "인구 밀집 시간대", "인구 분산 시간대")
 * @param congestIconUrl 혼잡도 상태를 나타내는 아이콘 이미지 URL
 * @param timeData 시간 정보 Triple (시각, 미사용, 몇 시간 후)
 * @param forecastText 예측 정보 텍스트 데이터 (인구 상태, 혼잡도, 색상)
 */

@Composable
fun CongestionTimeBox(
    subTitleText: String,
    congestIconUrl: String,
    timeData: Triple<String, String, String>,
    forecastText: PopulationForecastTextData,
) {

    Box(modifier = Modifier.padding(start = AppSpacing.Medium, top = AppSpacing.Medium)) {
        Column {
            Text(
                text = subTitleText,
                fontSize = AppFontSizes.TitleMedium,
                fontWeight = FontWeight.Bold,
                color = AppColors.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppSpacing.Medium)
            ) {
                AsyncImage(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    model = congestIconUrl,
                    contentDescription = "시간 아이콘"
                )

                Column(modifier = Modifier.padding(start = AppSpacing.Large)) {
                    PopulationForecastText(
                        timeData,
                        forecastText
                    )
                }
            }
        }
    }
}