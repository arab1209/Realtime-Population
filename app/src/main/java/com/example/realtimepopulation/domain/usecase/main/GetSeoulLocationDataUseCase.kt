package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.repository.SeoulLocationRepository
import javax.inject.Inject

class GetSeoulLocationDataUseCase @Inject constructor(
    private val repository: SeoulLocationRepository,
) {
    operator fun invoke(): List<LocationData> {
        return repository.getSeoulLocationData()
    }
}