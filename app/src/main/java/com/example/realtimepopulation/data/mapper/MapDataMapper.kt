package com.example.realtimepopulation.data.mapper

import com.example.realtimepopulation.data.dto.FcstPpltnItem
import com.example.realtimepopulation.data.dto.MapDataDto
import com.example.realtimepopulation.domain.model.map.ForecastData
import com.example.realtimepopulation.domain.model.map.MapData

fun MapDataDto.toDomain(): MapData {
    return MapData(
        areaName = this.seoulRtd.areaName ?: "Unknown",
        congestionLevel = this.seoulRtd.areaCongestLvl ?: "Unknown",
        congestionMessage = this.seoulRtd.areaCongestMsg ?: "No Data",
        minCount = this.seoulRtd.minCount ?: "No Data",
        maxCount = this.seoulRtd.maxCount ?: "No Data",
        forecasts = this.seoulRtd.fcstPpltn.fcstPpltnList.map { it.toDomain() }
    )
}

fun FcstPpltnItem.toDomain(): ForecastData {
    return ForecastData(
        time = this.fcstTime ?: "Unknown time",
        congestionLevel = this.fcstCongestLvl ?: "Unknown level",
        minPopulation = this.fcstPpltnMin ?: 0,
        maxPopulation = this.fcstPpltnMax ?: 0
    )
}
