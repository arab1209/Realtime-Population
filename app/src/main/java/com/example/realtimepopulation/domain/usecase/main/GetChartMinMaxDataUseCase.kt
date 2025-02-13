package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class GetChartMinMaxDataUseCase @Inject constructor() {
    operator fun invoke(detailScreenData: MapData): Int {
        val maxY = detailScreenData.forecasts.flatMap {
            listOf(it.minPopulation, it.maxPopulation)
        }.maxOrNull() ?: 100

        return when (maxY) {
            in 0..15000 -> 25000
            in 15001..35000 -> 50000
            in 35001..80000 -> 100000
            in 80001..130000 -> 150000
            else -> 2500000
        }
    }
}