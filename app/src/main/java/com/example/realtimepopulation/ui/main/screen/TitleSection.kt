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

@Composable
fun TitleSection() {
    Box(modifier = Modifier.padding(top = AppSpacing.XLarge, start = AppSpacing.XLarge)) {// 상단 나라, 지역 이름
        Column() {
            Text(
                fontSize = AppFontSizes.Small, color = AppColors.Black, text = TitleSectionDimens.CountryName
            )
            Text(
                fontSize = AppFontSizes.Title, color = AppColors.Black, text = TitleSectionDimens.RegionName
            )
        }
    }
}