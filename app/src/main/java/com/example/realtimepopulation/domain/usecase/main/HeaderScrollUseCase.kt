package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.domain.model.main.ScrollStateData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HeaderScrollUseCase @Inject constructor() {
    operator fun invoke(delta: Float, currentOffset: Float, maxHeight: Float): Float {
        return calculateHeaderOffset(delta, currentOffset, maxHeight)
    }

    fun handlePostScroll(delta: Float, currentOffset: Float, maxHeight: Float): Float {
        return when {
            delta > 0 && currentOffset != 0f -> 0f
            delta < 0 && currentOffset != -maxHeight -> -maxHeight
            else -> currentOffset
        }
    }

    fun updateHeaderOffset(scrollState: MutableStateFlow<ScrollStateData>, offset: Float) {
        scrollState.update { it.copy(headerOffset = offset) }
    }

    private fun calculateHeaderOffset(delta: Float, currentOffset: Float, maxHeight: Float): Float {
        return (currentOffset + delta).coerceIn(-maxHeight, 0f)
    }
}