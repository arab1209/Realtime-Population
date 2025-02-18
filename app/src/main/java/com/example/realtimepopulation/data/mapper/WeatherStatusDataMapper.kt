package com.example.realtimepopulation.data.mapper

import WeatherStatusDataDto
import com.example.realtimepopulation.domain.model.main.CityData
import com.example.realtimepopulation.domain.model.main.WeatherForecast
import com.example.realtimepopulation.domain.model.main.WeatherStts
import com.example.realtimepopulation.domain.model.main.WeatherSttsData

fun WeatherStatusDataDto.toDomain(): WeatherSttsData {
    return WeatherSttsData(
        cityData = CityData(
            weatherStts = WeatherStts(
                weatherTime = cityData?.weatherSttsWrapper?.weatherStts?.weatherTime.orEmpty(),
                temp = cityData?.weatherSttsWrapper?.weatherStts?.temp.orEmpty(),
                sensibleTemp = cityData?.weatherSttsWrapper?.weatherStts?.sensibleTemp.orEmpty(),
                maxTemp = cityData?.weatherSttsWrapper?.weatherStts?.maxTemp.orEmpty(),
                minTemp = cityData?.weatherSttsWrapper?.weatherStts?.minTemp.orEmpty(),
                humidity = cityData?.weatherSttsWrapper?.weatherStts?.humidity.orEmpty(),
                windDirection = cityData?.weatherSttsWrapper?.weatherStts?.windDirection.orEmpty(),
                windSpeed = cityData?.weatherSttsWrapper?.weatherStts?.windSpeed.orEmpty(),
                precipitation = cityData?.weatherSttsWrapper?.weatherStts?.precipitation.orEmpty(),
                precptType = cityData?.weatherSttsWrapper?.weatherStts?.precptType.orEmpty(),
                pcpMsg = cityData?.weatherSttsWrapper?.weatherStts?.pcpMsg.orEmpty(),
                sunrise = cityData?.weatherSttsWrapper?.weatherStts?.sunrise.orEmpty(),
                sunset = cityData?.weatherSttsWrapper?.weatherStts?.sunset.orEmpty(),
                uvIndexLvl = cityData?.weatherSttsWrapper?.weatherStts?.uvIndexLvl.orEmpty(),
                uvIndex = cityData?.weatherSttsWrapper?.weatherStts?.uvIndex.orEmpty(),
                uvMsg = cityData?.weatherSttsWrapper?.weatherStts?.uvMsg.orEmpty(),
                pm25Index = cityData?.weatherSttsWrapper?.weatherStts?.pm25Index.orEmpty(),
                pm25 = cityData?.weatherSttsWrapper?.weatherStts?.pm25.orEmpty(),
                pm10Index = cityData?.weatherSttsWrapper?.weatherStts?.pm10Index.orEmpty(),
                pm10 = cityData?.weatherSttsWrapper?.weatherStts?.pm10.orEmpty(),
                airIndex = cityData?.weatherSttsWrapper?.weatherStts?.airIndex.orEmpty(),
                airIdxMvl = cityData?.weatherSttsWrapper?.weatherStts?.airIdxMvl.orEmpty(),
                airIdxMain = cityData?.weatherSttsWrapper?.weatherStts?.airIdxMain.orEmpty(),
                airMsg = cityData?.weatherSttsWrapper?.weatherStts?.airMsg.orEmpty(),
                forecast = cityData?.weatherSttsWrapper?.weatherStts?.forecastWrapper?.forecasts?.map {
                    WeatherForecast(
                        fcstDt = it.fcstDt.orEmpty(),
                        temp = it.temp.orEmpty(),
                        precipitation = it.precipitation.orEmpty(),
                        precptType = it.precptType.orEmpty(),
                        rainChance = it.rainChance.orEmpty(),
                        skyStts = it.skyStts.orEmpty()
                    )
                } ?: emptyList()
            )
        )
    )
}