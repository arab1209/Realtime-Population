package com.example.realtimepopulation.domain.model.map

data class MapData(
    val areaName: String,
    val congestionLevel: String,
    val congestionMessage: String,
    val minCount: String,
    val maxCount: String,
    val maleRate: String,
    val femaleRate: String,
    val populationRate0s: String,
    val populationRate10s: String,
    val populationRate20s: String,
    val populationRate30s: String,
    val populationRate40s: String,
    val populationRate50s: String,
    val populationRate60s: String,
    val populationRate70s: String,
    val resident: String,
    val nonResident: String,
    val forecasts: List<ForecastData>
)

data class ForecastData(
    val time: String,
    val congestionLevel: String,
    val minPopulation: Int,
    val maxPopulation: Int
)