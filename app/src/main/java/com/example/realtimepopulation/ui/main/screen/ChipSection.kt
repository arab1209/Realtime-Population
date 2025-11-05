package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

private val AREA_TYPES = listOf("관광특구", "고궁·문화유산", "인구밀집지역", "발달상권", "공원")

/**
 * 지역 필터 칩 섹션
 *
 * 서울시 구별 필터링을 위한 가로 스크롤 칩 리스트입니다.
 * 사용자가 칩을 선택하면 해당 구의 데이터만 필터링되어 표시됩니다.
 *
 * @param selectedChip 현재 선택된 칩 (구 이름, 예: "전체", "강남구", ...)
 * @param onChipSelected 칩 선택 시 호출되는 콜백 (선택된 구 이름 전달)
 */

@Composable
fun ChipSection(
    selectedChip: String,
    onChipSelected: (String) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = AppSpacing.MediumLarge, end = AppSpacing.MediumLarge),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.Large)
        ) {
            AREA_TYPES.forEach { area ->
                AssistChip(
                    onClick = {
                        onChipSelected(area)
                    },
                    label = {
                        Text(
                            area, fontSize = AppFontSizes.LabelSmall
                        )
                    },
                    border = BorderStroke(0.dp, Color.Transparent),
                    shape = RoundedCornerShape(AppSpacing.Large),
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selectedChip == area) AppColors.LightBlue else Color.Transparent,
                        labelColor = if (selectedChip == area) AppColors.Blue else AppColors.Gray
                    )
                )
            }
        }
    }
}