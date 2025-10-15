package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.detail.AirQualityData
import com.example.realtimepopulation.domain.repository.AirQualityRepository
import javax.inject.Inject

class GetAirQualityDataUseCase @Inject constructor(
    private val repository: AirQualityRepository
) {
    suspend operator fun invoke(regionName: String): AirQualityData {
        return repository.getAirQuality(regionName)
    }
}