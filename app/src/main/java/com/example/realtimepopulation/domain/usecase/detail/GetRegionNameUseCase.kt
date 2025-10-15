package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class GetRegionNameUseCase @Inject constructor() {
    operator fun invoke(seoulLocationData: List<LocationData>, detailScreenData: MapData?): String {
        return seoulLocationData.find {
            it.areaName == detailScreenData?.areaName
        }?.region.toString()
    }
}