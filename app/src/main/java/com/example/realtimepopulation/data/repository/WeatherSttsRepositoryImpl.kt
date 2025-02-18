package com.example.realtimepopulation.data.repository

import com.example.realtimepopulation.data.mapper.toDomain
import com.example.realtimepopulation.di.api.SeoulAreaApiService
import com.example.realtimepopulation.domain.model.main.WeatherSttsData
import com.example.realtimepopulation.domain.repository.WeatherSttsRepository
import javax.inject.Inject

class WeatherStatusRepositoryImpl @Inject constructor(
    private val seoulAreaApiService: SeoulAreaApiService
): WeatherSttsRepository{
    override suspend fun getWeatherSttsData(areaName: String): Result<WeatherSttsData> {
        return runCatching {
            seoulAreaApiService.getWeatherStts(areaName).body()?.toDomain()!!
        }
    }
}