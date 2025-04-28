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
import androidx.compose.ui.graphics.StrokeCap
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
    strokeWidth: Float = 300f,
    showLegend: Boolean = false,
) {
    segments.forEach {
        Log.d("test", it.value.toString())
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                val width = size.width
                val height = size.height
                val radius = min(width / 2f, height)
                val centerX = width / 2f
                val centerY = height

                // 데이터 세그먼트 그리기
                var startAngle = 180f
                val total = segments.sumOf { it.value.toDouble() }.toFloat()

                segments.forEach { segment ->
                    val sweepAngle = (segment.value / total) * 180f

                    drawArc(
                        color = segment.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(radius * 2f, radius * 2f),
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                    )

                    startAngle += sweepAngle
                }
            }
        }

        // 레전드 (범례) 표시
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