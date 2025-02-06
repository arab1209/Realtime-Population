package com.example.realtimepopulation.domain.usecase

import com.example.realtimepopulation.di.api.SeoulAreaApiService
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class GetAreaPopulationDataUseCase @Inject constructor(
    private val seoulAreaApiService: SeoulAreaApiService,
) {
    suspend operator fun invoke(areaData: List<LocationData>): List<MapData> {
        val temp = mutableListOf<MapData>()
        areaData.map { area ->
            runCatching {
                seoulAreaApiService.getPopulationData(area.areaName)
            }.onSuccess { response ->
                response.body()?.let {
                    temp.add(it)
                }
            }
        }
        return temp
    }
}