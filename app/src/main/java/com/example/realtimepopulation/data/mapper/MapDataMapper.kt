package com.example.realtimepopulation.data.mapper

import com.example.realtimepopulation.data.dto.MapDataDto
import com.example.realtimepopulation.domain.model.map.MapData

fun MapDataDto.toDomain(): MapData {
    return MapData(
        areaName = this.seoulRtd.areaName ?: "Unknown",
        congestionLevel = this.seoulRtd.areaCongestLvl ?: "Unknown",
        congestionMessage = this.seoulRtd.areaCongestMsg ?: "No Data"
    )
}