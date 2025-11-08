package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

/**
 * 상세 화면의 섹션 제목을 표시하는 컴포넌트
 *
 * 각 정보 섹션(인구 추이, 인구 구성 비율 등)의 제목을
 *
 * @param titleText 표시할 제목 텍스트
 */

@Composable
fun PopulationTitleBox(titleText: String) {
    Text(
        text = titleText,
        fontSize = AppFontSizes.TitleLarge,
        fontWeight = FontWeight.Bold,
        color = Color(0xff4c65a7),
        modifier = Modifier.padding(start = AppSpacing.Medium, top = AppSpacing.Medium)
    )
}