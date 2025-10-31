package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.map.ForecastData
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.ceil

class AnalyzeMaxTimeUseCase @Inject constructor() {
    operator fun invoke(detailScreenData: List<ForecastData>): Triple<String, String, String> {
        val currentTime = LocalTime.now()

        // 현재 시간 이후의 데이터만 필터링
        val futureForecasts = detailScreenData.filter {
            val forecastTime = LocalTime.parse(it.time.substring(11, 16))
            forecastTime.isAfter(currentTime) || forecastTime == currentTime
        }

        // 필터링된 데이터 중 최대값 찾기
        val maxPopulationData = futureForecasts.maxByOrNull { it.maxPopulation }
            ?: return Triple("00시", "0", "0")

        val maxTime = maxPopulationData.time.substring(11, 16) // "14:00"
        val maxTimeFormatted = LocalTime.parse(maxTime)
            .format(DateTimeFormatter.ofPattern("HH시")) // "14시"

        // 시간 차이 계산 (항상 양수)
        val minutesLeft = currentTime.until(LocalTime.parse(maxTime), ChronoUnit.MINUTES)
        val hoursLeft = ceil(minutesLeft / 60.0).toInt()

        return Triple(
            maxTimeFormatted,
            maxPopulationData.maxPopulation.toString(),
            hoursLeft.toString()
        )
    }
}