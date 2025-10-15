package com.example.realtimepopulation.data.repository

import com.example.realtimepopulation.data.mapper.toDomain
import com.example.realtimepopulation.di.api.SeoulAirQualityApiService
import com.example.realtimepopulation.domain.model.detail.AirQualityData
import com.example.realtimepopulation.domain.repository.AirQualityRepository
import javax.inject.Inject

class AirQualityRepositoryImpl @Inject constructor(
    private val seoulAirQualityApiService: SeoulAirQualityApiService
) : AirQualityRepository {

    override suspend fun getAirQuality(regionName: String): AirQualityData {
        return seoulAirQualityApiService.getAirQualityData("20251015", regionName).toDomain()
    }
}