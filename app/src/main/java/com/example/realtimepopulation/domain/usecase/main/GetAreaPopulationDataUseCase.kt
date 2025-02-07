package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.domain.repository.PopulationRepository
import javax.inject.Inject

class GetAreaPopulationDataUseCase @Inject constructor(
    private val repository: PopulationRepository,
) {
    suspend operator fun invoke(areaData: List<LocationData>): List<MapData> {
        return areaData.mapNotNull { area ->
            repository.getPopulationData(area.areaName).getOrNull()
        }
    }
}