package com.example.realtimepopulation.domain.model.map

data class MapData(
    val areaName: String,
    val congestionLevel: String,
    val congestionMessage: String,
    val minCount: String,
    val maxCount: String,
    val maleRate: String,
    val femaleRate: String,
    val populationRate0s: Float,
    val populationRate10s: Float,
    val populationRate20s: Float,
    val populationRate30s: Float,
    val populationRate40s: Float,
    val populationRate50s: Float,
    val populationRate60s: Float,
    val populationRate70s: Float,
    val forecasts: List<ForecastData>
)

data class ForecastData(
    val time: String,
    val congestionLevel: String,
    val minPopulation: Int,
    val maxPopulation: Int
)