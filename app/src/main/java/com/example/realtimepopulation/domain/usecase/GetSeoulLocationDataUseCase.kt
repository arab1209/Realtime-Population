package com.example.realtimepopulation.domain.usecase

import com.example.realtimepopulation.domain.repository.SeoulLocationRepository
import com.example.realtimepopulation.domain.model.main.LocationData
import javax.inject.Inject

class GetSeoulLocationDataUseCase @Inject constructor(
    private val repository: SeoulLocationRepository,
) {
    operator fun invoke(category: String): Pair<List<LocationData>, List<LocationData>> {
        return Pair(repository.getSeoulLocationData(), repository.getSeoulLocationData().filter {
            it.category == category
        })
    }
}