package com.example.realtimepopulation.domain.model.map

data class MapData(
    val areaName: String,
    val congestionLevel: String,
    val congestionMessage: String,
    val forecasts: List<ForecastData>
)

data class ForecastData(
    val time: String,
    val congestionLevel: String,
    val minPopulation: Int,
    val maxPopulation: Int
)