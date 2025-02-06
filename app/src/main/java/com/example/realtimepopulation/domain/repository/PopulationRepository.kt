package com.example.realtimepopulation.domain.repository

import com.example.realtimepopulation.domain.model.map.MapData

interface PopulationRepository {
    suspend fun getPopulationData(areaName: String): Result<MapData>
}