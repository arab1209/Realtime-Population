package com.example.realtimepopulation.data.mapper

import com.example.realtimepopulation.data.dto.FcstPpltnItem
import com.example.realtimepopulation.data.dto.MapDataDto
import com.example.realtimepopulation.domain.model.map.ForecastData
import com.example.realtimepopulation.domain.model.map.MapData

fun MapDataDto.toDomain(): MapData {
    return MapData(
        areaName = this.seoulRtd.areaName ?: "",
        congestionLevel = this.seoulRtd.areaCongestLvl ?: "",
        congestionMessage = this.seoulRtd.areaCongestMsg ?: "",
        minCount = this.seoulRtd.minCount ?: "",
        maxCount = this.seoulRtd.maxCount ?: "",
        maleRate = this.seoulRtd.maleRate ?: "",
        femaleRate = this.seoulRtd.femaleRate ?: "",
        populationRate0s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate10s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate20s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate30s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate40s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate50s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate60s = this.seoulRtd.populationRate0s ?: 0.0f,
        populationRate70s = this.seoulRtd.populationRate0s ?: 0.0f,
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
