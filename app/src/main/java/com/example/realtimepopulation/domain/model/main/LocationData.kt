package com.example.realtimepopulation.domain.model.main

data class LocationData(
    val category: String,
    val areaName: String,
    val lat: Double,
    val long: Double,
    val imgURL: String,
    val region: String
)
