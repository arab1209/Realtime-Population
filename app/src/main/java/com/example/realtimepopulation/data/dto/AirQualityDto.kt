package com.example.realtimepopulation.data.dto

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "TimeAverageAirQuality")
data class AirQualityDto(
    @Element(name = "row")
    val latestTemperature: LatestTemperature?
)

@Xml(name = "row")
data class LatestTemperature(
    @PropertyElement(name = "NO2")
    val no2: String?,

    @PropertyElement(name = "O3")
    val o3: String?,

    @PropertyElement(name = "CO")
    val c0: String?,

    @PropertyElement(name = "SO2")
    val so2: String?
)
