package com.example.realtimepopulation.di.api

import WeatherStatusDataDto
import com.example.realtimepopulation.data.dto.MapDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulAreaApiService {
    @GET("xml/citydata_ppltn/1/5/{location}")
    suspend fun getPopulationData(
        @Path("location") location: String
    ): Response<MapDataDto>

    @GET("xml/citydata/1/5/{location}")
    suspend fun getWeatherStts(
        @Path("location") location:String
    ): Response<WeatherStatusDataDto>
}