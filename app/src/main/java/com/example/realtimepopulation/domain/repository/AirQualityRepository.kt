package com.example.realtimepopulation.domain.repository

import com.example.realtimepopulation.domain.model.detail.AirQualityData

interface AirQualityRepository {
    suspend fun getAirQuality(regionName: String): AirQualityData
}