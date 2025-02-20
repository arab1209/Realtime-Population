package com.example.realtimepopulation.ui.main.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimepopulation.domain.model.main.ChartData
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.main.ScrollStateData
import com.example.realtimepopulation.domain.model.main.WeatherSttsData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.domain.usecase.main.CalcAreaColorUseCase
import com.example.realtimepopulation.domain.usecase.main.GetAreaPopulationDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetChartDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetChartMinMaxDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetCongestMessageUseCase
import com.example.realtimepopulation.domain.usecase.main.GetDetailScreenDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetScreenNavRouteUseCase
import com.example.realtimepopulation.domain.usecase.main.GetSelectChipDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetSeoulLocationDataUseCase
import com.example.realtimepopulation.domain.usecase.main.GetWeatherSttsUseCase
import com.example.realtimepopulation.domain.usecase.main.HeaderScrollUseCase
import com.example.realtimepopulation.ui.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSeoulLocationDataUseCase: GetSeoulLocationDataUseCase,
    private val getAreaPopulationDataUseCase: GetAreaPopulationDataUseCase,
    private val getSelectChipDataUseCase: GetSelectChipDataUseCase,
    private val getScreenNavRouteUseCase: GetScreenNavRouteUseCase,
    private val headerScrollUseCase: HeaderScrollUseCase,
    private val calcAreaColorUseCase: CalcAreaColorUseCase,
    private val getDetailScreenDataUseCase: GetDetailScreenDataUseCase,
    private val getCongestMessageUseCase: GetCongestMessageUseCase,
    private val getChartMinMaxDataUseCase: GetChartMinMaxDataUseCase,
    private val getChartDataUseCase: GetChartDataUseCase,
    private val getWeatherSttsUseCase: GetWeatherSttsUseCase,
) : ViewModel() {
    private val _seoulLocationData = MutableStateFlow<List<LocationData>>(emptyList())
    val seoulLocationData = _seoulLocationData.asStateFlow()

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    val areaTypes = listOf("관광특구", "고궁·문화유산", "인구밀집지역", "발달상권", "공원")

    private val _selectChip = MutableStateFlow<String?>(areaTypes.first())
    val selectChip = _selectChip.asStateFlow()

    private val _selectChipData = MutableStateFlow<List<LocationData>>(emptyList())
    val selectChipData = _selectChipData.asStateFlow()

    private val _populationData = MutableStateFlow<List<MapData>>(emptyList())
    val populationData = _populationData.asStateFlow()

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex = _selectedIndex.asStateFlow()

    val navItems = listOf<Screen>(Screen.Home, Screen.Map)

    private val _scrollState = MutableStateFlow(ScrollStateData())
    val scrollState = _scrollState.asStateFlow()

    private val _detailScreenData = MutableLiveData<MapData>()
    val detailScreenData: LiveData<MapData> get() = _detailScreenData

    private val _congestMessage = MutableStateFlow<List<String>>(emptyList())
    val congestMessage = _congestMessage.asStateFlow()

    private val _chartMinMax = MutableStateFlow<Int>(100)
    val chartMinMax = _chartMinMax.asStateFlow()

    private val _chartData = MutableStateFlow<List<ChartData>>(emptyList())
    val chartData = _chartData.asStateFlow()

    private val _weatherSttsData = MutableLiveData<WeatherSttsData>()
    val weatherSttsData: LiveData<WeatherSttsData> get() = _weatherSttsData

    init {
        readSeoulAreasFromExcel()
    }

    fun readSeoulAreasFromExcel() {
        viewModelScope.launch {
            _seoulLocationData.value = getSeoulLocationDataUseCase()
            _selectChipData.value =
                getSelectChipDataUseCase(_seoulLocationData.value, areaTypes.first())
            _populationData.value = getAreaPopulationDataUseCase(_seoulLocationData.value)

        }
    }

    fun setQueryText(text: String) {
        _searchQuery.value = text
    }

    fun setSelectChip(text: String) {
        _selectChip.value = text
        _selectChipData.value = getSelectChipDataUseCase(_seoulLocationData.value, text)
    }

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun getIndexForRoute(route: String?): Int {
        return getScreenNavRouteUseCase(route)
    }

    fun handlePreScroll(delta: Float, maxHeight: Float) {
        headerScrollUseCase.updateHeaderOffset(
            _scrollState, headerScrollUseCase(delta, _scrollState.value.headerOffset, maxHeight)
        )
    }

    fun handlePostScroll(delta: Float, maxHeight: Float) {
        headerScrollUseCase.updateHeaderOffset(
            _scrollState,
            headerScrollUseCase.handlePostScroll(delta, _scrollState.value.headerOffset, maxHeight)
        )
    }

    fun calcAreaColor(congestLvl: String): Color {
        return calcAreaColorUseCase(congestLvl)
    }

    fun setDetailScreenData(mapData: List<MapData>, query: String) {
        _detailScreenData.value = getDetailScreenDataUseCase(mapData, query)
    }

    fun getCongestMessage(query: String) {
        _congestMessage.value = getCongestMessageUseCase(query)
    }

    fun getChartData(detailScreenData: MapData) {
        _chartMinMax.value = getChartMinMaxDataUseCase(detailScreenData)
        _chartData.value = getChartDataUseCase(detailScreenData)
    }

    fun getWeatherStatus(areaName: String) {
        viewModelScope.launch {
            _weatherSttsData.value = getWeatherSttsUseCase(areaName)
        }
    }
}