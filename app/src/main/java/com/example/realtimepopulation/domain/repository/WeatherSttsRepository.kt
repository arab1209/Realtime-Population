package com.example.realtimepopulation.domain.repository

import com.example.realtimepopulation.domain.model.main.WeatherSttsData

interface WeatherSttsRepository {
    suspend fun getWeatherSttsData(areaName: String): Result<WeatherSttsData>
}