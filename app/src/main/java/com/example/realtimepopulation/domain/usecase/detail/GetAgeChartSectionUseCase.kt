package com.example.realtimepopulation.domain.usecase.detail

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.example.realtimepopulation.domain.model.detail.AgeDistributionChartUiModel
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegment
import javax.inject.Inject

class GetAgeChartSectionUseCase @Inject constructor() {
    @SuppressLint("DefaultLocale")
    operator fun invoke(data: AgeDistributionChartUiModel): ChartSectionData {
        val ageSegments = listOf(
            ChartSegment(
                value = data.populationRate10s,
                color = Color(0xFFFFC107), // 10대 이하 - 노란색
                label = "10대이하"
            ),
            ChartSegment(
                value = data.populationRate20s,
                color = Color(0xFFFF9800), // 20대 - 주황색
                label = "20대"
            ),
            ChartSegment(
                value = data.populationRate30s,
                color = Color(0xFFE86B7C), // 30대 - 분홍색
                label = "30대"
            ),
            ChartSegment(
                value = data.populationRate40s,
                color = Color(0xFFBA68C8), // 40대 - 보라색
                label = "40대"
            ),
            ChartSegment(
                value = data.populationRate50s,
                color = Color(0xFF6C63FF), // 50대 - 파란색
                label = "50대"
            ),
            ChartSegment(
                value = data.populationRate60s,
                color = Color(0xFF4DD0E1), // 60대 이상 - 청록색
                label = "60대이상"
            )
        )

        val maxAgeSegment = ageSegments.maxByOrNull { it.value }
        val ageSummary = if (maxAgeSegment != null) {
            "전체 연령대 중 ${maxAgeSegment.label}가 ${String.format("%.1f", maxAgeSegment.value)}%로 가장 많아요"
        } else {
            null
        }

        return ChartSectionData(
            title = "연령대별 비율",
            segments = ageSegments,
            summary = ageSummary
        )
    }
}