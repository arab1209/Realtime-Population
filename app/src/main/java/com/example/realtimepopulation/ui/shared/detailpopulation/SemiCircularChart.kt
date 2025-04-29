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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.realtimepopulation.domain.model.detail.ChartSegment
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun SemiCircularChart(
    segments: List<ChartSegment>,
    modifier: Modifier = Modifier,
    showLegend: Boolean = false,
    innerRadiusRatio: Float = 0.1f,
    startAngleDegrees: Float = 180f,
    sweepAngleDegrees: Float = 180f
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                val outerRadius = min(width / 2f, height) * 0.9f
                val innerRadius = outerRadius * innerRadiusRatio
                val centerX = width / 2f
                val centerY = height

                drawArc(
                    color = Color.Transparent,
                    startAngle = startAngleDegrees,
                    sweepAngle = sweepAngleDegrees,
                    useCenter = false,
                    topLeft = Offset(centerX - outerRadius, centerY - outerRadius),
                    size = Size(outerRadius * 2f, outerRadius * 2f),
                    style = Stroke(width = outerRadius - innerRadius, cap = StrokeCap.Butt)
                )

                val donutPath = Path()
                val total = segments.sumOf { it.value.toDouble() }.toFloat()
                var currentAngle = startAngleDegrees

                segments.forEach { segment ->
                    val segmentAngle = (segment.value / total) * sweepAngleDegrees
                    donutPath.reset()

                    val rect = Rect(
                        left = centerX - outerRadius,
                        top = centerY - outerRadius,
                        right = centerX + outerRadius,
                        bottom = centerY + outerRadius
                    )

                    donutPath.addArc(
                        oval = rect,
                        startAngleDegrees = currentAngle,
                        sweepAngleDegrees = segmentAngle
                    )

                    drawPath(
                        path = donutPath,
                        color = segment.color,
                        style = Stroke(width = outerRadius - innerRadius, cap = StrokeCap.Butt)
                    )

                    currentAngle += segmentAngle
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

// Path 확장 함수
fun Path.addArc(
    oval: Rect,
    startAngleDegrees: Float,
    sweepAngleDegrees: Float
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
