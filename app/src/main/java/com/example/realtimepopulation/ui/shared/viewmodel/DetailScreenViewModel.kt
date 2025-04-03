package com.example.realtimepopulation.ui.shared.viewmodel

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.domain.model.map.ForecastData
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeCongestIconUrlUscase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeCongestLevelUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeMaxTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeMinTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeTextUseCase
import com.example.realtimepopulation.domain.usecase.detail.CalcTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetFirstTabColorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val calcTimeUseCase: CalcTimeUseCase,
    private val getFirstTabColorUseCase: GetFirstTabColorUseCase,
    private val analyzeTextUseCase: AnalyzeTextUseCase,
    private val analyzeCongestLevelUseCase: AnalyzeCongestLevelUseCase,
    private val analyzeCongestIconUrlUscase: AnalyzeCongestIconUrlUscase,
    private val analyzeMaxTimeUseCase: AnalyzeMaxTimeUseCase,
    private val analyzeMinTimeUseCase: AnalyzeMinTimeUseCase,
) : ViewModel() {

    private val _congestLevelImgUrl = MutableStateFlow("")
    val congestLevelImgUrl = _congestLevelImgUrl.asStateFlow()

    private val _congestIconUrl = MutableStateFlow("")
    val congestIconUrl = _congestIconUrl.asStateFlow()

    private val _maxPopulationHour = MutableStateFlow(Triple("00:00", "0", "0"))
    val maxPopulationHour = _maxPopulationHour.asStateFlow()

    private val _minPopulationHour = MutableStateFlow(Triple("00:00", "0", "0"))
    val minPopulationHour = _minPopulationHour.asStateFlow()


    fun calcTime(time: String): String {
        return calcTimeUseCase(time)
    }

    fun getFirstTabColor(index: Int): Modifier {
        return getFirstTabColorUseCase(index)
    }

    fun analyzeText(text: String): Int {
        return analyzeTextUseCase(text)
    }

    fun analyzeCongestImgUrl(congestLevel: String) {
        _congestLevelImgUrl.value = analyzeCongestLevelUseCase(congestLevel)
    }

    fun analyzeCongestIconUrl(flag: Int) {
        _congestIconUrl.value = analyzeCongestIconUrlUscase(flag)
    }

    fun analyzeMaxMinPopulation(detailScreenData: List<ForecastData>) {
        getMaxTimePopulation(detailScreenData)
        getMinTimePopulation(detailScreenData)
    }

    private fun getMaxTimePopulation(detailScreenData: List<ForecastData>) {
        Log.d("test max", analyzeMaxTimeUseCase(detailScreenData).toString())
        _maxPopulationHour.value = analyzeMaxTimeUseCase(detailScreenData)
    }

    private fun getMinTimePopulation(detailScreenData: List<ForecastData>) {
        Log.d("test", analyzeMinTimeUseCase(detailScreenData).toString())
        _minPopulationHour.value = analyzeMinTimeUseCase(detailScreenData)
    }
}