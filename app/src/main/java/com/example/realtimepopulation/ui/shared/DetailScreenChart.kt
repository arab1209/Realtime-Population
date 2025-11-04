package com.example.realtimepopulation.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realtimepopulation.domain.model.main.ChartData
import com.example.realtimepopulation.domain.model.map.ForecastData
import com.example.realtimepopulation.ui.main.viewmodel.MainViewModel
import com.example.realtimepopulation.ui.theme.AppSpacing
import com.example.realtimepopulation.ui.theme.AppSpacing.ExtraExtraExtraLarge
import com.example.realtimepopulation.ui.theme.AppSpacing.ExtraExtraLarge
import com.example.realtimepopulation.ui.theme.AppSpacing.Medium
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun DetailScreenChart(
    getAreaColor: (String) -> Color,
    foreCastData: List<ForecastData>?,
    maxData: Int,
    chartData: List<ChartData>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp)
    ) {
        Chart(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = Medium)
                .height(200.dp), chart = columnChart(
                columns = foreCastData?.map { dataItem ->
                    lineComponent(
                        color = getAreaColor(dataItem.congestionLevel),
                        thickness = 15.dp,
                        shape = Shapes.cutCornerShape(
                            topRightPercent = 20, topLeftPercent = 20
                        )
                    )
                } ?: emptyList() ,
                spacing = ExtraExtraLarge,
                dataLabel = TextComponent.Builder().build(),
                axisValuesOverrider = AxisValuesOverrider.fixed(
                    minY = 0f, maxY = maxData.toFloat()
                )
            ), chartModelProducer = ChartEntryModelProducer(chartData.map {
                entryOf(
                    it.index, it.xData
                )
            }), startAxis = rememberStartAxis(
                itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 6)
            ), bottomAxis = rememberBottomAxis(valueFormatter = { value, _ ->
                (chartData.map {
                    it.time
                }[value.toInt()])
            }), chartScrollState = rememberChartScrollState(),
            runInitialAnimation = false
        )
    }
}