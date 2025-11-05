package com.example.realtimepopulation.ui.main.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing
import com.example.realtimepopulation.ui.theme.TitleSectionDimens

/**
 * 메인 화면 상단 타이틀 섹션
 *
 * 국가명과 지역명을 표시합니다.
 * 예: "대한민국" / "서울"
 *
 * 앱의 지역적 범위를 명확히 보여주는 헤더 역할을 합니다.
 */

@Composable
fun TitleSection() {
    Box(modifier = Modifier.padding(top = AppSpacing.MediumLarge, start = AppSpacing.MediumLarge)) {// 상단 나라, 지역 이름
        Column() {
            Text(
                fontSize = AppFontSizes.LabelSmall, color = AppColors.Black, text = TitleSectionDimens.CountryName
            )
            Text(
                fontSize = AppFontSizes.HeadlineLarge, color = AppColors.Black, text = TitleSectionDimens.RegionName
            )
        }
    }
}