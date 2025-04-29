package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import javax.inject.Inject
import kotlin.math.min

class CalculateChartUseCase @Inject constructor() {
    operator fun invoke(width: Float, height: Float, config: ChartConfigData): ChartDimensionsData {
        val outerRadius = min(width / 2f, height) * 0.9f

        return ChartDimensionsData(
            width = width,
            height = height,
            outerRadius = outerRadius,
            innerRadius = outerRadius * config.innerRadiusRatio,
            centerX = width / 2f,
            centerY = height
        )
    }
}