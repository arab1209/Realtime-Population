package com.example.realtimepopulation.ui.shared.detailpopulation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentDrawaData
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import com.example.realtimepopulation.ui.theme.AppSpacing
import kotlin.math.cos
import kotlin.math.sin

/**
 * 반원형 차트를 그리는 Composable
 *
 * 인구 분포 데이터를 시각화하기 위해 사용됩니다.
 * 각 세그먼트는 비율에 따라 호(arc)로 표현됩니다.
 *
 * @param segments 차트에 표시할 데이터 세그먼트 리스트 (색상, 라벨, 값 포함)
 * @param modifier Composable의 레이아웃 수정자
 * @param showLegend 범례 표시 여부 (기본값: false)
 * @param calculateChart 차트 크기 계산 함수 (캔버스 크기 → 차트 치수 변환)
 * @param calculateSegmentDraw 각 세그먼트의 그리기 데이터 계산 함수
 */

@Composable
fun SemiCircularChart(
    segments: List<ChartSegmentData>,
    modifier: Modifier = Modifier,
    showLegend: Boolean = false,
    calculateChart: (Float, Float, ChartConfigData) -> ChartDimensionsData,
    calculateSegmentDraw: (List<ChartSegmentData>, ChartDimensionsData, ChartConfigData) -> List<ChartSegmentDrawaData>
) {
    var chartConfig by remember { mutableStateOf(ChartConfigData(0.1f, 180f, 180f)) }
    var chartDimensions by remember { mutableStateOf<ChartDimensionsData?>(null) }
    var segmentDrawData by remember { mutableStateOf<List<ChartSegmentDrawaData>>(emptyList()) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(horizontal = AppSpacing.Large, vertical = AppSpacing.Large)
                .onSizeChanged { size ->
                    val dimensions = calculateChart(
                        size.width.toFloat(),
                        size.height.toFloat(),
                        chartConfig
                    )
                    chartDimensions = dimensions
                    segmentDrawData = calculateSegmentDraw(
                        segments,
                        dimensions,
                        chartConfig
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                chartDimensions?.let { dimensions ->
                    drawArc(
                        color = Color.Transparent,
                        startAngle = chartConfig.startAngleDegrees,
                        sweepAngle = chartConfig.sweepAngleDegrees,
                        useCenter = false,
                        topLeft = Offset(
                            dimensions.centerX - dimensions.outerRadius,
                            dimensions.centerY - dimensions.outerRadius
                        ),
                        size = Size(dimensions.outerRadius * 2f, dimensions.outerRadius * 2f),
                        style = Stroke(
                            width = dimensions.outerRadius - dimensions.innerRadius,
                            cap = StrokeCap.Butt
                        )
                    )
                    segmentDrawData.forEach { drawData ->
                        drawPath(
                            path = drawData.path,
                            color = drawData.color,
                            style = Stroke(width = drawData.strokeWidth, cap = StrokeCap.Butt)
                        )
                    }
                }
            }
        }

        if (showLegend) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                segments.forEach { segment ->
                    LegendItem(
                        color = segment.color,
                        label = segment.label,
                        percentage = segment.value
                    )
                }
            }
        }
    }
}