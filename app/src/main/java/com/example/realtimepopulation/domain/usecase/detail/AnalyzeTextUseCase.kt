package com.example.realtimepopulation.domain.usecase.detail

import javax.inject.Inject

class AnalyzeTextUseCase @Inject constructor() {
    operator fun invoke(text: String): Int {
        return if(text == "실시간 인구") 1 else 2
    }
}