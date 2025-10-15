package com.example.realtimepopulation.domain.usecase.detail

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ResidentStatusChartUiModel
import javax.inject.Inject

class GetResidentStatusChartSectionUseCase @Inject constructor() {
    @SuppressLint("DefaultLocale")
    operator fun invoke(data: ResidentStatusChartUiModel): ChartSectionData {
        val residentSegments = listOf(
            ChartSegmentData(
                value = data.resident.toFloat(),
                color = Color(0xFF51aaae),
                label = "상주"
            ),
            ChartSegmentData(
                value = data.nonResident.toFloat(),
                color = Color(0xFFac3ea0),
                label = "비상주"
            )
        )

        val residentValue = data.resident.toFloatOrNull() ?: 0f
        val nonResidentValue = data.nonResident.toFloatOrNull() ?: 0f
        val residentDifference = nonResidentValue - residentValue

        val genderSummary = if (residentDifference > 0) {
            "비상주 인원이 상주 인원 보다 ${String.format("%.1f", residentDifference)}% 많아요."
        } else if (residentDifference < 0) {
            "상주 인원이 비상주 인원 보다 ${String.format("%.1f", -residentDifference)}% 많아요."
        } else {
            "상주 인원과 비상주 인원의 비율이 같아요."
        }

        return ChartSectionData(
            title = "상주·비상주 비율",
            segments = residentSegments,
            summary = genderSummary
        )
    }
}