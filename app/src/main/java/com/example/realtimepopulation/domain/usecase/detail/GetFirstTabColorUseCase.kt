package com.example.realtimepopulation.domain.usecase.detail

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import javax.inject.Inject

class GetFirstTabColorUseCase @Inject constructor() {
    operator fun invoke(index: Int): Modifier {
        return if (index == 0) {
            Modifier.background(Color(0xffe5eef4))
        } else {
            Modifier
        }
    }
}