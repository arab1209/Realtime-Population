package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.map.ForecastData
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.ceil


class AnalyzeMaxTimeUseCase @Inject constructor() {
    operator fun invoke(detailScreenData: List<ForecastData>): Triple<String, String, String> {
        val currentTime = LocalTime.now()

        // 미래 시간 중 최대 인구수 데이터 찾기
        val maxPopulationData = detailScreenData
            .maxByOrNull { it.maxPopulation }
            ?: return Triple("00시", "0", "0")

        val forecastTime = LocalTime.parse(maxPopulationData.time.substring(11, 16))

        // 남은 시간 계산 (음수면 다음날)
        val minutesLeft = currentTime.until(forecastTime, ChronoUnit.MINUTES)
            .let { if (it < 0) it + 24 * 60 else it }

        return Triple(
            forecastTime.format(DateTimeFormatter.ofPattern("HH시")),
            maxPopulationData.maxPopulation.toString(),
            ceil(minutesLeft / 60.0).toInt().toString()
        )
    }
}