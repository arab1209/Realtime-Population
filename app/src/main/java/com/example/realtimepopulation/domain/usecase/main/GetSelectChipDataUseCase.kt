package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.LocationData
import javax.inject.Inject

class GetSelectChipDataUseCase @Inject constructor(
){
    operator fun invoke(seoulLocationData: List<LocationData>, query: String): List<LocationData> {
        return seoulLocationData.filter { it.category == query }
    }
}