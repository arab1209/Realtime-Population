package com.example.realtimepopulation.ui.shared.detailpopulation

import android.util.Log
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimepopulation.domain.model.detail.ChartSegment
import com.patrykandpatrick.vico.core.legend.LegendItem
import kotlin.math.min

@Composable
fun SemiCircularChart(
    segments: List<ChartSegment>,
    modifier: Modifier = Modifier,
    strokeWidth: Float = 60f,
    showLegend: Boolean = false,
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
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val width = size.width
                val height = size.height

                val halfStrokeWidth = strokeWidth / 2f
                val maxRadius = min(width / 2f - halfStrokeWidth, height - halfStrokeWidth)
                val radius = maxRadius * 0.9f

                val centerX = width / 2f
                val centerY = height

                var startAngle = 180f
                val total = segments.sumOf { it.value.toDouble() }.toFloat()

                val gapAngle = 1.5f // 조각 사이에 1.5도 틈을 준다

                segments.forEach { segment ->
                    val sweepAngle = (segment.value / total) * 180f - gapAngle

                    drawArc(
                        color = segment.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(radius * 2f, radius * 2f),
                        style = Stroke(width = 60f, cap = StrokeCap.Butt) // Butt로 유지
                    )

                    startAngle += sweepAngle + gapAngle // 다음 조각 시작할 때 gapAngle만큼 띄워줌
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
                    LegendItem(color = segment.color, label = segment.label, percentage = segment.value)
                }
            }
        }
    }
}