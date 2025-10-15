package com.example.realtimepopulation.domain.usecase.search

import com.example.realtimepopulation.domain.model.main.LocationData
import javax.inject.Inject

class SearchSeoulLocationUseCase @Inject constructor() {
    operator fun invoke(
        allData: List<LocationData>,
        query: String
    ): List<LocationData> {
        if(query.isBlank()) {
            return allData
        }

        val trimmedQuery = query.trim()
        return allData.filter { item ->
            item.areaName.contains(trimmedQuery, ignoreCase = true)
        }
    }
}