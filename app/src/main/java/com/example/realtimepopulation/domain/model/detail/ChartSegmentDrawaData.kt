package com.example.realtimepopulation.domain.model.detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

data class ChartSegmentDrawaData(
    val path: Path,
    val color: Color,
    val strokeWidth: Float
)
