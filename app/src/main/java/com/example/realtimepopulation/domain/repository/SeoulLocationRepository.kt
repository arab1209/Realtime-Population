package com.example.realtimepopulation.domain.repository

import com.example.realtimepopulation.domain.model.main.LocationData

interface SeoulLocationRepository {
    fun getSeoulLocationData(): List<LocationData>
}