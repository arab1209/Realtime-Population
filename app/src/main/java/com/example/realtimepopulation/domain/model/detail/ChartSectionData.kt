package com.example.realtimepopulation.domain.model.detail

data class ChartSectionData(
    val title: String,
    val segments: List<ChartSegmentData>,
    val summary: String? = null
)
