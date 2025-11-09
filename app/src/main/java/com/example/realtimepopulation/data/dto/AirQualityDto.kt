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
    @PropertyElement(name = "NTDX")
    val no2: String?,

    @PropertyElement(name = "OZON")
    val o3: String?,

    @PropertyElement(name = "CBMX")
    val co: String?,

    @PropertyElement(name = "SPDX")
    val so2: String?
)