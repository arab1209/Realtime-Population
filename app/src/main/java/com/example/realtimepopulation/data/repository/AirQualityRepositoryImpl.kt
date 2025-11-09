package com.example.realtimepopulation.data.repository

import com.example.realtimepopulation.data.mapper.toDomain
import com.example.realtimepopulation.di.api.SeoulAirQualityApiService
import com.example.realtimepopulation.domain.model.detail.AirQualityData
import com.example.realtimepopulation.domain.repository.AirQualityRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AirQualityRepositoryImpl @Inject constructor(
    private val seoulAirQualityApiService: SeoulAirQualityApiService
) : AirQualityRepository {

    override suspend fun getAirQuality(regionName: String): AirQualityData {
        return seoulAirQualityApiService.getAirQualityData(
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
            regionName
        ).toDomain()
    }
}