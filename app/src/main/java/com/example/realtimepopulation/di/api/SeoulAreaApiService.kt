package com.example.realtimepopulation.di.api

import com.example.realtimepopulation.data.main.MapData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulAreaApiService {
    @GET("xml/citydata_ppltn/1/5/{location}")
    suspend fun getPopulationData(
        @Path("location") location: String
    ): Response<MapData>
}