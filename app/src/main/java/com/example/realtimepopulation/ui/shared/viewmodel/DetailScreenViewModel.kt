package com.example.realtimepopulation.ui.shared.viewmodel

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.domain.model.detail.ChartConfigData
import com.example.realtimepopulation.domain.model.detail.ChartDimensionsData
import com.example.realtimepopulation.domain.model.detail.ChartSectionData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentData
import com.example.realtimepopulation.domain.model.detail.ChartSegmentDrawaData
import com.example.realtimepopulation.domain.model.detail.PopulationDistributionData
import com.example.realtimepopulation.domain.model.main.PopulationForecastTextData
import com.example.realtimepopulation.domain.model.map.ForecastData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeCongestIconUrlUscase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeCongestLevelUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeMaxTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeMinTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.AnalyzeTextUseCase
import com.example.realtimepopulation.domain.usecase.detail.CalcTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.CalculateChartUseCase
import com.example.realtimepopulation.domain.usecase.detail.CalculateSegmentDrawUseCase
import com.example.realtimepopulation.domain.usecase.detail.GenderDistributionUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetAgeChartSectionUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetFirstTabColorUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetResidentStatusChartSectionUseCase
import com.example.realtimepopulation.domain.usecase.detail.MapDataToPopulationDistributionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val analyzeTextUseCase: AnalyzeTextUseCase,
    private val analyzeCongestLevelUseCase: AnalyzeCongestLevelUseCase,
    private val analyzeCongestIconUrlUscase: AnalyzeCongestIconUrlUscase,
    private val analyzeMaxTimeUseCase: AnalyzeMaxTimeUseCase,
    private val analyzeMinTimeUseCase: AnalyzeMinTimeUseCase,
    private val mapDataToPopulationDistributionUseCase: MapDataToPopulationDistributionUseCase,
    private val genderDistributionUseCase: GenderDistributionUseCase,
    private val getAgeChartSectionUseCase: GetAgeChartSectionUseCase,
    private val calculateChartUseCase: CalculateChartUseCase,
    private val calculateSegmentDrawUseCase: CalculateSegmentDrawUseCase,
    private val getResidentStatusChartSectionUseCase: GetResidentStatusChartSectionUseCase
) : ViewModel() {

    private val _congestLevelImgUrl = MutableStateFlow("")
    val congestLevelImgUrl = _congestLevelImgUrl.asStateFlow()

    private val _maxPopulationHour = MutableStateFlow(Triple("00:00", "0", "0"))
    val maxPopulationHour = _maxPopulationHour.asStateFlow()

    private val _minPopulationHour = MutableStateFlow(Triple("00:00", "0", "0"))
    val minPopulationHour = _minPopulationHour.asStateFlow()

    private val _congestIconUrl0 = MutableStateFlow("")
    val congestIconUrl0 = _congestIconUrl0.asStateFlow()

    private val _congestIconUrl1 = MutableStateFlow("")
    val congestIconUrl1 = _congestIconUrl1.asStateFlow()

    private val _chartModel = MutableStateFlow<PopulationDistributionData?>(null)

    private val _genderChartSection = MutableStateFlow<ChartSectionData?>(null)
    val genderChartSection = _genderChartSection.asStateFlow()

    private val _ageChartSection = MutableStateFlow<ChartSectionData?>(null)
    val ageChartSection = _ageChartSection.asStateFlow()

    private val _residentChartSection = MutableStateFlow<ChartSectionData?>(null)
    val residentChartSection = _residentChartSection.asStateFlow()

    fun analyzeText(text: String): Int {
        return analyzeTextUseCase(text)
    }

    fun analyzeCongestImgUrl(congestLevel: String) {
        _congestLevelImgUrl.value = analyzeCongestLevelUseCase(congestLevel)
    }

    fun analyzeCongestIconUrl(flag: Int) {
        when (flag) {
            0 -> _congestIconUrl0.value = analyzeCongestIconUrlUscase(0)
            1 -> _congestIconUrl1.value = analyzeCongestIconUrlUscase(1)
        }
    }

    fun analyzeMaxMinPopulation(detailScreenData: List<ForecastData>) {
        _maxPopulationHour.value = analyzeMaxTimeUseCase(detailScreenData)
        _minPopulationHour.value = analyzeMinTimeUseCase(detailScreenData)
    }


    fun getForecastText(flag: Int): PopulationForecastTextData {
        return when (flag) {
            0 -> PopulationForecastTextData("많고", "높을", "붐빔", "일 것으로 예상돼요", Color(0xFFFF5675))
            else -> PopulationForecastTextData("적고", "낮을", "여유", "로울 것으로 예상돼요", Color(0xFF80E12A))
        }
    }

    fun toDomainModel(detailScreenData: MapData) {
        _chartModel.value = mapDataToPopulationDistributionUseCase(detailScreenData)
        _genderChartSection.value = genderDistributionUseCase(_chartModel.value!!.genderDistributionChartUiModel)
        _ageChartSection.value = getAgeChartSectionUseCase(_chartModel.value!!.ageDistributionChartUiModel)
        _residentChartSection.value = getResidentStatusChartSectionUseCase(_chartModel.value!!.residentStatusChartUiModel)
    }

    fun calculateChart(width: Float, height: Float, config: ChartConfigData): ChartDimensionsData {
        return calculateChartUseCase(width, height, config)
    }

    fun calculateSegmentDraw(
        segments: List<ChartSegmentData>,
        dimensions: ChartDimensionsData,
        config: ChartConfigData
    ): List<ChartSegmentDrawaData> {
        return calculateSegmentDrawUseCase(segments, dimensions, config)
    }
}