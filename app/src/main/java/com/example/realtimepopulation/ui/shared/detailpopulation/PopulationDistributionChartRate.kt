package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.realtimepopulation.domain.model.detail.ChartSegment
import com.example.realtimepopulation.domain.model.detail.PopulationDistributionData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@Composable
fun PopulationDistributionChartRate(viewModel: DetailScreenViewModel) {
    val genderChartSection by viewModel.genderChartSection.collectAsState()
    val ageChartSection by viewModel.ageChartSection.collectAsState()

    genderChartSection?.let { ChartSection(data = it) }
    ageChartSection?.let { ChartSection(data = it) }
}