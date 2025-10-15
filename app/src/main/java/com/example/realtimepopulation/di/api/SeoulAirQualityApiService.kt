package com.example.realtimepopulation.di.api

import com.example.realtimepopulation.data.dto.AirQualityDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulAirQualityApiService {
    @GET("xml/TimeAverageAirQuality/1/1/{todayDate}/{district}")
    suspend fun getAirQualityData(
        @Path("todayDate") todayDate: String, @Path("district") district: String
    ): AirQualityDto
}