package com.example.realtimepopulation.domain.usecase.main

import android.util.Log
import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class GetDetailScreenDataUseCase @Inject constructor() {
    operator fun invoke(mapDataList: List<MapData>, query: String): MapData {
        return mapDataList.find { it.areaName == query }!!
    }
}