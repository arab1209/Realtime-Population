package com.example.realtimepopulation.ui.shared.detailpopulation

import android.util.Log
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

@Composable
fun ChartSection(
    data: ChartSectionData,
    calculateChartUseCase: (Float, Float, ChartConfigData) -> ChartDimensionsData,
    calculateSegmentDrawUseCase: (List<ChartSegmentData>, ChartDimensionsData, ChartConfigData) -> List<ChartSegmentDrawaData>
) {
    Text(
        modifier = Modifier.padding(top = 15.dp, start = 10.dp),
        text = data.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black
    )

    Spacer(modifier = Modifier.height(16.dp))

    SemiCircularChart(
        segments = data.segments,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        showLegend = true,
        calculateChartUseCase = calculateChartUseCase,
        calculateSegmentDrawUseCase = calculateSegmentDrawUseCase
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (data.summary != null) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                        text = "• ${data.summary}", fontSize = 14.sp, color = Color.Gray
            )
        }
    }
}