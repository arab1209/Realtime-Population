package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

/**
 * 차트의 범례(Legend) 항목을 표시하는 컴포넌트
 *
 * 원형 차트의 각 세그먼트가 무엇을 의미하는지 색상 박스와 함께
 * 레이블 및 백분율로 표시합니다.
 *
 * UI 구조:
 * [색상 박스] 레이블
 *      XX.X%
 *
 * @param color 차트 세그먼트와 매칭되는 색상 (범례 박스 색상)
 * @param label 항목 이름 (예: "남성", "20대", "거주민" 등)
 * @param percentage 해당 항목의 비율 (0~100 사이의 값)
 */

@Composable
fun LegendItem(
    color: Color,
    label: String,
    percentage: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(AppSpacing.Large)
                    .background(color = color, shape = MaterialTheme.shapes.small)
            )
            Text(
                text = label,
                fontSize = AppFontSizes.BodySmall,
                color = Color.Black
            )
        }
        Text(
            text = String.format("%.1f%%", percentage), // 퍼센트 표시
            fontSize = AppFontSizes.BodySmall,
            color = AppColors.Gray
        )
    }
}
