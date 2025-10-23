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

@Composable
fun ChipSection(viewModel: MainViewModel) {
    val selectChip by viewModel.selectChip.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = AppSpacing.MediumLarge, end = AppSpacing.MediumLarge),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.Large)
        ) {
            viewModel.areaTypes.forEach { area ->
                AssistChip(
                    onClick = {
                        viewModel.setSelectChip(area)
                    },
                    label = {
                        Text(
                            area, fontSize = AppFontSizes.LabelSmall
                        )
                    },
                    border = BorderStroke(0.dp, Color.Transparent),
                    shape = RoundedCornerShape(AppSpacing.Large),
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selectChip == area) AppColors.LightBlue else Color.Transparent,
                        labelColor = if (selectChip == area) AppColors.Blue else AppColors.Gray
                        )
                    )
            }
        }
    }
}