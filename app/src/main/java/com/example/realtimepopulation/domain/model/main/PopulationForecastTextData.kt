package com.example.realtimepopulation.domain.model.main

import androidx.compose.ui.graphics.Color

data class PopulationForecastTextData(
    val peakText: String,
    val congestionText: String,
    val levelText: String,
    val levelEndText: String,
    val color: Color
)