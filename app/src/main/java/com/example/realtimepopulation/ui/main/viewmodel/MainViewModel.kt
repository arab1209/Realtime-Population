package com.example.realtimepopulation.ui.main.viewmodel

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimepopulation.domain.model.detail.AirQualityData
import com.example.realtimepopulation.domain.model.main.ChartData
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.main.ScrollStateData
import com.example.realtimepopulation.domain.model.main.WeatherSttsData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.domain.usecase.detail.CalcTimeUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetAirQualityDataUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetFirstTabColorUseCase
import com.example.realtimepopulation.domain.usecase.detail.GetRegionNameUseCase
import com.example.realtimepopulation.domain.usecase.main.CalcAreaColorUseCase
import com.example.realtimepopulation.domain.usecase.main.ConvertPopulationDataToMapUseCase
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
import com.example.realtimepopulation.domain.usecase.search.SearchSeoulLocationUseCase
import com.example.realtimepopulation.ui.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
    private val getRegionNameUseCase: GetRegionNameUseCase,
    private val getAirQualityDataUseCase: GetAirQualityDataUseCase,
    private val searchSeoulLocationUseCase: SearchSeoulLocationUseCase,
    private val calcTimeUseCase: CalcTimeUseCase,
    private val getFirstTabColorUseCase: GetFirstTabColorUseCase,
    private val convertPopulationDataToMapUseCase: ConvertPopulationDataToMapUseCase
) : ViewModel() {
    private val _seoulLocationData = MutableStateFlow<List<LocationData>>(emptyList())
    val seoulLocationData = _seoulLocationData.asStateFlow()


    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectChip = MutableStateFlow("관광특구")
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

    private val _airMsg = MutableStateFlow("")
    val airMsg = _airMsg.asStateFlow()

    private val _regionName = MutableStateFlow("")
    val regionName = _regionName.asStateFlow()

    private val _airQualityData = MutableLiveData<AirQualityData>()
    val airQualityData: LiveData<AirQualityData> get() = _airQualityData

    private val _savedSearchQuery = MutableStateFlow("")
    val savedSearchQuery = _savedSearchQuery.asStateFlow()

    private val _searchList = MutableStateFlow<List<LocationData>>(emptyList())
    val searchList = _searchList.asStateFlow()

    val populationDataMap: StateFlow<Map<String, MapData>> = _populationData
        .map { list -> convertPopulationDataToMapUseCase(list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyMap()
        )

    init {
        readSeoulAreasFromExcel()
    }

    fun readSeoulAreasFromExcel() {
        viewModelScope.launch {
            _seoulLocationData.value = getSeoulLocationDataUseCase()
            _selectChipData.value =
                getSelectChipDataUseCase(_seoulLocationData.value, selectChip.value.toString())
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
        val newOffset = headerScrollUseCase(
            delta = delta,
            currentOffset = _scrollState.value.headerOffset,
            maxHeight = maxHeight
        )
        updateHeaderOffset(newOffset)
    }

    fun handlePostScroll(delta: Float, maxHeight: Float) {
        val newOffset = headerScrollUseCase.handlePostScroll(
            delta = delta,
            currentOffset = _scrollState.value.headerOffset,
            maxHeight = maxHeight
        )
        updateHeaderOffset(newOffset)
    }

    private fun updateHeaderOffset(offset: Float) {
        _scrollState.update { it.copy(headerOffset = offset) }
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

    fun updateSafetyMessage(message: String) {
        _airMsg.value = message.replace(".", ".\n")
    }

    fun getRegionName() {
        _regionName.value = getRegionNameUseCase(seoulLocationData.value, detailScreenData.value)
    }

    fun getAirQualityData(regionName: String) {
        viewModelScope.launch {
            _airQualityData.value = getAirQualityDataUseCase(regionName)
        }
    }

    fun saveSearchQuery(query: String) {
        _savedSearchQuery.value = query
    }

    fun searchLocationData(query: String) {
        _searchList.value = searchSeoulLocationUseCase(_seoulLocationData.value, query)
    }

    fun calcTime(time: String): String {
        return calcTimeUseCase(time)
    }

    fun getFirstTabColor(index: Int): Modifier {
        return getFirstTabColorUseCase(index)
    }
}