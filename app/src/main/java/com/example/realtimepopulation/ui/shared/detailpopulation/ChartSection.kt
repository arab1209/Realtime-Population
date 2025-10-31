package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentDrawaData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import com.example.realtimepopulation.ui.theme.AppColors
import com.example.realtimepopulation.ui.theme.AppFontSizes
import com.example.realtimepopulation.ui.theme.AppSpacing

@Composable
fun ChartSection(
    data: ChartSectionData,
    calculateChart: (Float, Float, ChartConfigData) -> ChartDimensionsData,
    calculateSegmentDraw: (List<ChartSegmentData>, ChartDimensionsData, ChartConfigData) -> List<ChartSegmentDrawaData>
) {
    Text(
        modifier = Modifier.padding(top = AppSpacing.Large, start = AppSpacing.Medium),
        text = data.title,
        fontSize = AppFontSizes.TitleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(AppSpacing.Large))

    SemiCircularChart(
        segments = data.segments,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        showLegend = true,
        calculateChart = calculateChart,
        calculateSegmentDraw = calculateSegmentDraw
    )

    Spacer(modifier = Modifier.height(AppSpacing.Small))

    if (data.summary != null) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "• ${data.summary}",
                fontSize = AppFontSizes.TitleSmall,
                color = AppColors.Gray
            )
        }
    }
}