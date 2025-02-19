package com.example.realtimepopulation.domain.usecase.main

import androidx.compose.ui.graphics.Color
import javax.inject.Inject

class CalcAreaColorUseCase @Inject constructor(){
    operator fun invoke(congestLevel: String): Color {
        return when (congestLevel) {
            in listOf("붐빔", "매우나쁨") -> Color(0xFFFF5675)
            in listOf("약간 붐빔", "나쁨") -> Color(0xFFFF9100)
            "보통" -> Color(0xFFFFD232)
            else -> Color(0xFF80E12A)
        }
    }
}