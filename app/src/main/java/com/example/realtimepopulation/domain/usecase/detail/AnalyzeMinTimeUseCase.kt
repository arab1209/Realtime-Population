package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.map.ForecastData
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.ceil

class AnalyzeMinTimeUseCase @Inject constructor() {
    operator fun invoke(detailScreenData: List<ForecastData>): Triple<String, String, String> {
        val minPopulationData =
            detailScreenData.minByOrNull { it.maxPopulation } ?: return Triple("00시", "0", "0")

        val minTime = minPopulationData.time.substring(11, 16)
        val now = LocalTime.now()

        val minLocalTime = LocalTime.parse(minTime)
        val minTimeFormatted = minLocalTime.format(DateTimeFormatter.ofPattern("HH시"))

        val minutesLeft = calculateMinutesDifference(now, minLocalTime)
        val hoursLeft = ceil(minutesLeft / 60.0).toInt()

        return Triple(
            minTimeFormatted, minPopulationData.maxPopulation.toString(), hoursLeft.toString()
        )
    }

    private fun calculateMinutesDifference(current: LocalTime, target: LocalTime): Long {
        var minutesLeft = current.until(target, ChronoUnit.MINUTES)

        if (minutesLeft < 0) {
            minutesLeft += 24 * 60
        }

        return minutesLeft
    }
}