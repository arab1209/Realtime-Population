package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class ConvertPopulationDataToMapUseCase @Inject constructor() {
    operator fun invoke(populationList: List<MapData>):  Map<String, MapData> {
        return populationList.associateBy { it.areaName }
    }
}