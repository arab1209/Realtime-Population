package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.map.ForecastData
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.ceil

class AnalyzeMaxTimeUseCase @Inject constructor() {
    operator fun invoke(detailScreenData: List<ForecastData>): Triple<String, String, String> {
        val maxPopulationData =
            detailScreenData.maxByOrNull { it.maxPopulation } ?: return Triple("00시", "0", "0")

        val maxTime = maxPopulationData.time.substring(11, 16) // "14:00" 또는 "01:00"
        val currentTime =
            LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) // 현재 시간 "11:06"

        // 24시간제 + 앞에 0을 붙이는 형식
        val maxTimeFormatted =
            LocalTime.parse(maxTime).format(DateTimeFormatter.ofPattern("HH시")) // "14시" 또는 "01시"

        // 시간 차이 계산 후 올림
        val minutesLeft =
            LocalTime.parse(currentTime).until(LocalTime.parse(maxTime), ChronoUnit.MINUTES)
        val hoursLeft = ceil(minutesLeft / 60.0).toInt()

        return Triple(
            maxTimeFormatted, maxPopulationData.maxPopulation.toString(), hoursLeft.toString()
        )
    }
}