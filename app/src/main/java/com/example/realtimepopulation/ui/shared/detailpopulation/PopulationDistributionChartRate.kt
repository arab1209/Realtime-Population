package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentDrawaData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@Composable
fun PopulationDistributionChartRate(
    genderChartSection: ChartSectionData?,
    ageChartSection: ChartSectionData?,
    residentChartSection: ChartSectionData?,
    calculateChart: (Float, Float, ChartConfigData) -> ChartDimensionsData,
    calculateSegmentDraw: (List<ChartSegmentData>, ChartDimensionsData, ChartConfigData) -> List<ChartSegmentDrawaData>
) {
    genderChartSection?.let {
        ChartSection(
            data = it,
            calculateChart = calculateChart,
            calculateSegmentDraw = calculateSegmentDraw
        )
    }

    ageChartSection?.let {
        ChartSection(
            data = it,
            calculateChart = calculateChart,
            calculateSegmentDraw = calculateSegmentDraw
        )
    }

    residentChartSection?.let {
        ChartSection(
            data = it,
            calculateChart = calculateChart,
            calculateSegmentDraw = calculateSegmentDraw
        )
    }
}