package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.detail.GenderChartUiModel
import javax.inject.Inject

class GenderUseCase @Inject constructor() {
    operator fun invoke(
        canvasWidth: Float, canvasHeight: Float, malePercentage: Float, femalePercentage: Float
    ): GenderChartUiModel {
        val diameter = canvasWidth.coerceAtMost(canvasHeight * 2)
        val outerRadius = diameter / 2
        val innerRadius = outerRadius * 0.3f
        val centerX = canvasWidth / 2
        val centerY = canvasHeight

        val maleAngle = 180f * malePercentage / 100f
        val femaleAngle = 180f * femalePercentage / 100f

        return GenderChartUiModel(
            maleAngle = maleAngle,
            femaleAngle = femaleAngle,
            outerRadius = outerRadius,
            innerRadius = innerRadius,
            centerX = centerX,
            centerY = centerY,
            diameter = diameter
        )
    }
}