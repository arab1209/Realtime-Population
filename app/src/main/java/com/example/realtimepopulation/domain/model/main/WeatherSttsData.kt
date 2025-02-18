package com.example.realtimepopulation.domain.model.main

data class WeatherSttsData(
    val cityData: CityData
)

data class CityData(
    val weatherStts: WeatherStts
)

data class WeatherStts(
    val weatherTime: String,
    val temp: String,
    val sensibleTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val humidity: String,
    val windDirection: String,
    val windSpeed: String,
    val precipitation: String,
    val precptType: String,
    val pcpMsg: String,
    val sunrise: String,
    val sunset: String,
    val uvIndexLvl: String,
    val uvIndex: String,
    val uvMsg: String,
    val pm25Index: String,
    val pm25: String,
    val pm10Index: String,
    val pm10: String,
    val airIndex: String,
    val airIdxMvl: String,
    val airIdxMain: String,
    val airMsg: String,
    val forecast: List<WeatherForecast>
)

data class WeatherForecast(
    val fcstDt: String,
    val temp: String,
    val precipitation: String,
    val precptType: String,
    val rainChance: String,
    val skyStts: String
)
