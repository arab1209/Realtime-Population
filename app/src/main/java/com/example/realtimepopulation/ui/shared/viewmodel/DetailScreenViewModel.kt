package com.example.realtimepopulation.ui.shared.viewmodel

import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeCongestLevelUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeTextUseCase
import com.example.realtimepopulation.domain.usecase.detail.CalcTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetFirstTabColorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val calcTimeUseCase: CalcTimeUseCase,
    private val getFirstTabColorUseCase: GetFirstTabColorUseCase,
    private val analyzeTextUseCase: AnalyzeTextUseCase,
    private val analyzeCongestLevelUseCase: AnalyzeCongestLevelUseCase,
) : ViewModel() {

    fun calcTime(time: String): String {
        return calcTimeUseCase(time)
    }

    fun getFirstTabColor(index: Int): Modifier {
        return getFirstTabColorUseCase(index)
    }

    fun analyzeText(text: String): Int {
        return analyzeTextUseCase(text)
    }

    fun analyzeImgUrl(congestLevel: String): String {
        return analyzeCongestLevelUseCase(congestLevel)
    }
}