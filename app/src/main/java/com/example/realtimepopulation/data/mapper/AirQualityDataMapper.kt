package com.example.realtimepopulation.data.mapper

import com.example.realtimepopulation.data.dto.AirQualityDto
import com.example.realtimepopulation.domain.model.detail.AirQualityData

fun AirQualityDto.toDomain(): AirQualityData {
    return AirQualityData(
        no2 = this.latestTemperature?.no2 ?: "",
        o3 = this.latestTemperature?.o3 ?: "",
        c0 = this.latestTemperature?.c0 ?: "",
        so2 = this.latestTemperature?.so2 ?: ""
    )
}