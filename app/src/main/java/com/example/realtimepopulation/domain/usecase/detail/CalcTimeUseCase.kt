package com.example.realtimepopulation.domain.usecase.detail

import javax.inject.Inject

class CalcTimeUseCase @Inject constructor() {
    operator fun invoke(time: String): String {
        return time.takeLast(4).take(2) + "시"
    }
}