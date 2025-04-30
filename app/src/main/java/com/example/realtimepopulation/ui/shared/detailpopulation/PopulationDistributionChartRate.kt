package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

@Composable
fun PopulationDistributionChartRate(viewModel: DetailScreenViewModel) {
    val genderChartSection by viewModel.genderChartSection.collectAsState()
    val ageChartSection by viewModel.ageChartSection.collectAsState()
    val residentChartSection by viewModel.residentChartSection.collectAsState()

    genderChartSection?.let {
        ChartSection(
            data = it,
            viewModel
        )
    }
    ageChartSection?.let {
        ChartSection(
            data = it,
            viewModel
        )
    }

    residentChartSection?.let {
        ChartSection(
            data = it,
            viewModel
        )
    }
}