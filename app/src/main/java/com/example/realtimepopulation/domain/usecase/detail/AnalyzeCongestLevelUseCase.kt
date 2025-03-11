package com.example.realtimepopulation.domain.usecase.detail

import javax.inject.Inject

class AnalyzeCongestLevelUseCase @Inject constructor() {
    operator fun invoke(congestLevel: String): String {
        return "https://data.seoul.go.kr/SeoulRtd/images/icon/graphic_popul_${
            mapOf("붐빔" to "04", "약간 붐빔" to "03", "보통" to "02")[congestLevel] ?: "01"
        }.jpg"
    }
}