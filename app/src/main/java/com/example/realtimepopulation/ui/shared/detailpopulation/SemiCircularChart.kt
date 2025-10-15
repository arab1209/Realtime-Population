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
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SemiCircularChart(
    segments: List<ChartSegmentData>,
    modifier: Modifier = Modifier,
    showLegend: Boolean = false,
    viewModel: DetailScreenViewModel
) {
    var chartConfig by remember { mutableStateOf(ChartConfigData(0.1f, 180f, 180f)) }
    var chartDimensions by remember { mutableStateOf<ChartDimensionsData?>(null) }
    var segmentDrawData by remember { mutableStateOf<List<ChartSegmentDrawaData>>(emptyList()) }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .onSizeChanged { size ->
                    val dimensions = viewModel.calculateChart(
                        size.width.toFloat(),
                        size.height.toFloat(),
                        chartConfig
                    )
                    chartDimensions = dimensions
                    segmentDrawData = viewModel.calculateSegmentDraw(
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

// 기존의 Path 확장 함수는 그대로 유지
fun Path.addArc(
    oval: Rect, startAngleDegrees: Float, sweepAngleDegrees: Float
): Path {
    val startAngle = Math.toRadians(startAngleDegrees.toDouble()).toFloat()
    val endAngle = Math.toRadians((startAngleDegrees + sweepAngleDegrees).toDouble()).toFloat()

    val centerX = oval.center.x
    val centerY = oval.center.y
    val radiusX = oval.width / 2f
    val radiusY = oval.height / 2f

    val startX = centerX + radiusX * cos(startAngle)
    val startY = centerY + radiusY * sin(startAngle)

    moveTo(startX, startY)

    arcTo(
        oval,
        startAngleDegrees = startAngleDegrees,
        sweepAngleDegrees = sweepAngleDegrees,
        forceMoveTo = false
    )
    return this
}
