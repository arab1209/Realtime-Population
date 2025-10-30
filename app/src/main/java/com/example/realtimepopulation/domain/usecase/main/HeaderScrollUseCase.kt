package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.ScrollStateData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HeaderScrollUseCase @Inject constructor() {

    // PreScroll 계산
    operator fun invoke(
        delta: Float,
        currentOffset: Float,
        maxHeight: Float
    ): Float {
        return calculateHeaderOffset(delta, currentOffset, maxHeight)
    }

    // PostScroll 계산
    fun handlePostScroll(
        delta: Float,
        currentOffset: Float,
        maxHeight: Float
    ): Float {
        return when {
            delta > 0 && currentOffset != 0f -> 0f
            delta < 0 && currentOffset != -maxHeight -> -maxHeight
            else -> currentOffset
        }
    }

    private fun calculateHeaderOffset(
        delta: Float,
        currentOffset: Float,
        maxHeight: Float
    ): Float {
        return (currentOffset + delta).coerceIn(-maxHeight, 0f)
    }
}