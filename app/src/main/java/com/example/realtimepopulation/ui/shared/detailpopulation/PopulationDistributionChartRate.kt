package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentDrawaData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel

/**
 * 인구 구성 비율을 시각화하는 차트 섹션들을 표시하는 컨테이너 컴포넌트
 *
 * 성별, 연령대, 거주민/비거주민 비율을 각각 독립적인 원형 차트로 표시합니다.
 * 각 차트는 데이터가 존재할 때만 렌더링됩니다.
 *
 * @param genderChartSection 성별(남성/여성) 구성 비율 차트 데이터
 * @param ageChartSection 연령대별(10대/20대/30대 등) 구성 비율 차트 데이터
 * @param residentChartSection 거주민/비거주민 구성 비율 차트 데이터
 * @param calculateChart 차트의 크기와 위치를 계산하는 함수
 * @param calculateSegmentDraw 차트의 각 세그먼트(부채꼴)를 그리기 위한 데이터를 계산하는 함수
 */

@Composable
fun PopulationDistributionChartRate(
    genderChartSection: ChartSectionData?,
    ageChartSection: ChartSectionData?,
    residentChartSection: ChartSectionData?,
    calculateChart: (Float, Float, ChartConfigData) -> ChartDimensionsData,
    calculateSegmentDraw: (List<ChartSegmentData>, ChartDimensionsData, ChartConfigData) -> List<ChartSegmentDrawaData>
) {
    genderChartSection?.let {
        ChartSection(
            data = it,
            calculateChart = calculateChart,
            calculateSegmentDraw = calculateSegmentDraw
        )
    }

    ageChartSection?.let {
        ChartSection(
            data = it,
            calculateChart = calculateChart,
            calculateSegmentDraw = calculateSegmentDraw
        )
    }

    residentChartSection?.let {
        ChartSection(
            data = it,
            calculateChart = calculateChart,
            calculateSegmentDraw = calculateSegmentDraw
        )
    }
}