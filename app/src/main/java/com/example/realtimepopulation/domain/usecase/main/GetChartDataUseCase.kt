package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.ChartData
import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class GetChartDataUseCase @Inject constructor() {
    operator fun invoke(detailScreenData: MapData): List<ChartData> {
        return detailScreenData.forecasts.mapIndexed { index, forecast ->
            ChartData(
                index = index.toFloat(),
                xData = forecast.maxPopulation.toFloat(),
                time = forecast.time.substringAfter(" ")
            )
        }
    }
}