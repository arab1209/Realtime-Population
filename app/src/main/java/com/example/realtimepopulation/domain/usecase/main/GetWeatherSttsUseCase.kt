package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.WeatherSttsData
import com.example.realtimepopulation.domain.repository.WeatherSttsRepository
import javax.inject.Inject

class GetWeatherSttsUseCase @Inject constructor(
    private val weatherSttsRepository: WeatherSttsRepository
) {
    suspend operator fun invoke(areaName: String): WeatherSttsData {
        return weatherSttsRepository.getWeatherSttsData(areaName).getOrNull()!!
    }
}