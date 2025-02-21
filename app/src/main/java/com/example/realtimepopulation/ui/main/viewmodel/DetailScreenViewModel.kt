package com.example.realtimepopulation.ui.main.viewmodel

import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.domain.usecase.detail.CalcTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetFirstTabColorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val calcTimeUseCase: CalcTimeUseCase,
    private val getFirstTabColorUseCase: GetFirstTabColorUseCase,
) : ViewModel() {

    fun calcTime(time: String): String {
        return calcTimeUseCase(time)
    }

    fun getFirstTabColor(index: Int): Modifier {
        return getFirstTabColorUseCase(index)
    }
}