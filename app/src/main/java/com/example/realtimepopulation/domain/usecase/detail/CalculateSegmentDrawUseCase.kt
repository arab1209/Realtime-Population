package com.example.realtimepopulation.domain.usecase.detail

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentDrawaData
import javax.inject.Inject

class CalculateSegmentDrawUseCase @Inject constructor() {
    operator fun invoke(
        segments: List<ChartSegmentData?>, dimensions: ChartDimensionsData, config: ChartConfigData
    ): List<ChartSegmentDrawaData> {
        val total = segments.sumOf { it!!.value.toDouble() }.toFloat()
        var currentAngle = config.startAngleDegrees
        val drawDataList = mutableListOf<ChartSegmentDrawaData>()

        segments.forEach { segment ->
            val segmentAngle = (segment!!.value / total) * config.sweepAngleDegrees
            val path = Path()

            val rect = Rect(
                left = dimensions.centerX - dimensions.outerRadius,
                top = dimensions.centerY - dimensions.outerRadius,
                right = dimensions.centerX + dimensions.outerRadius,
                bottom = dimensions.centerY + dimensions.outerRadius
            )

            path.addArc(
                oval = rect, startAngleDegrees = currentAngle, sweepAngleDegrees = segmentAngle
            )

            drawDataList.add(
                ChartSegmentDrawaData(
                    path = path,
                    color = segment.color,
                    strokeWidth = dimensions.outerRadius - dimensions.innerRadius
                )
            )

            currentAngle += segmentAngle
        }

        return drawDataList
    }
}