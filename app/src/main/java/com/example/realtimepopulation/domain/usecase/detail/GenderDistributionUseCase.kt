package com.example.realtimepopulation.domain.usecase.detail

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.GenderDistribtuionChartUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenderDistributionUseCase @Inject constructor() {
    @SuppressLint("DefaultLocale")
    operator fun invoke(data: GenderDistribtuionChartUiModel): ChartSectionData {
        val maleValue = data.male.toFloatOrNull() ?: 0f
        val femaleValue = data.female.toFloatOrNull() ?: 0f
        val genderDifference = femaleValue - maleValue

        val genderSummary = if (genderDifference > 0) {
            "여성이 남성보다 ${String.format("%.1f", genderDifference)}% 많아요."
        } else if (genderDifference < 0) {
            "남성이 여성보다 ${String.format("%.1f", -genderDifference)}% 많아요."
        } else {
            "남성과 여성의 비율이 같아요."
        }

        return ChartSectionData(
            title = "성별 비율",
            segments = listOf(
                ChartSegmentData(
                    value = data.male.toFloatOrNull() ?: 0f,
                    color = Color(0xFF6C63FF), // 남성 - 파란색
                    label = "남성"
                ),
                ChartSegmentData(
                    value = femaleValue,
                    color = Color(0xFFE86B7C), // 여성 - 분홍색
                    label = "여성"
                )
            ),
            summary = genderSummary
        )
    }
}