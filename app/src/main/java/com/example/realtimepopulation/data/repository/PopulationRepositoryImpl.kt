package com.example.realtimepopulation.data.repository

import com.example.realtimepopulation.data.dto.MapDataDto
import com.example.realtimepopulation.data.mapper.toDomain
import com.example.realtimepopulation.di.api.SeoulAreaApiService
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.domain.repository.PopulationRepository
import retrofit2.Response
import javax.inject.Inject

class PopulationRepositoryImpl @Inject constructor(
    private val seoulAreaApiService: SeoulAreaApiService
) : PopulationRepository {
    override suspend fun getPopulationData(areaName: String): Result<MapData> {
        return runCatching {
            seoulAreaApiService.getPopulationData(areaName).body()?.toDomain()!!
        }
    }
}