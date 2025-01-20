package com.example.realtimepopulation.ui.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.data.main.MapData
import com.example.realtimepopulation.data.main.ScrollStateData
import com.example.realtimepopulation.di.api.SeoulAreaApiService
import com.example.realtimepopulation.ui.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val seoulAreaApiService: SeoulAreaApiService,
    application: Application,
) : ViewModel() {
    private val _seoulLocationData = MutableStateFlow<List<LocationData>>(emptyList())
    val seoulLocationData: StateFlow<List<LocationData>> get() = _seoulLocationData

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    val areaTypes = listOf("관광특구", "고궁·문화유산", "인구밀집지역", "발달상권", "공원")

    private val _selectChip = MutableStateFlow<String?>(areaTypes.first())
    val selectChip: StateFlow<String?> get() = _selectChip

    private val _selectChipData = MutableStateFlow<List<LocationData>>(emptyList())
    val selectChipData: StateFlow<List<LocationData>> get() = _selectChipData

    private val _populationData = MutableStateFlow<List<MapData>>(emptyList())
    val populationData: StateFlow<List<MapData>> get() = _populationData

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex = _selectedIndex.asStateFlow()

    val navItems = listOf<Screen>(
        Screen.Home, Screen.Map
    )

    private val _scrollState = MutableStateFlow(ScrollStateData())
    val scrollState = _scrollState.asStateFlow()

    init {
        readSeoulAreasFromExcel(application)
    }

    fun readSeoulAreasFromExcel(context: Context) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    val sheet: Sheet =
                        WorkbookFactory.create(context.assets.open("seoul_important_regions.xlsx"))
                            .getSheetAt(0)

                    val temp = mutableListOf<LocationData>()
                    for (rowIndex in 1 until sheet.physicalNumberOfRows) {
                        val row = sheet.getRow(rowIndex)
                        if (row != null) {
                            temp.add(
                                LocationData(
                                    category = row.getCell(0)?.toString() ?: "",
                                    areaName = row.getCell(3)?.toString() ?: "",
                                    lat = row.getCell(4)?.toString()?.toDoubleOrNull() ?: 0.0,
                                    long = row.getCell(5)?.toString()?.toDoubleOrNull() ?: 0.0,
                                    imgURL = "https://data.seoul.go.kr/SeoulRtd/images/hotspot/${
                                        URLEncoder.encode(row.getCell(3)?.toString() ?: "", "UTF-8")
                                            .replace("+", "%20")
                                    }.jpg",
                                )
                            )
                        }
                    }
                    _seoulLocationData.value = temp // 서울지역 데이터 전체다 추가
                    _selectChipData.value =
                        _seoulLocationData.value.filter { it.category == areaTypes.first() } // 앱실행시 칩이 첫 번째 선택에 따른 데이터 셋팅
                    getAreaPopulationData(_seoulLocationData.value) // 앱실행시 서울지역 데이터 이름 기반으로 실시간 혼잡도 가져오기
                }
            }
        }
    }

    fun setQueryText(text: String) {
        _searchQuery.value = text
    }

    fun setSelectChip(text: String) {
        _selectChip.value = text
        _selectChipData.value = _seoulLocationData.value.filter { it.category == text }
    }

    private fun getAreaPopulationData(areaData: List<LocationData>) {
        val temp = mutableListOf<MapData>()

        viewModelScope.launch(Dispatchers.IO) {
            areaData.map { area ->
                async {
                    kotlin.runCatching {
                        seoulAreaApiService.getPopulationData(area.areaName)
                    }
                }
            }.awaitAll().forEach {
                it.onSuccess { response ->
                    response.body()?.let { area ->
                        temp.add(area)
                    }
                }
            }
            _populationData.value = temp
        }
    }

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun getIndexForRoute(route: String?): Int {
        return when (route) {
            Screen.Home.route -> 0
            Screen.Map.route -> 1
            else -> 0
        }
    }

    fun handlePreScroll(delta: Float, maxHeight: Float) {
        updateHeaderOffset(calculateHeaderOffset(delta, _scrollState.value.headerOffset, maxHeight))
    }

    fun handlePostScroll(delta: Float, maxHeight: Float) {
        if (delta > 0 && _scrollState.value.headerOffset != 0f) {
            updateHeaderOffset(0f)
        } else if (delta < 0 && _scrollState.value.headerOffset != -maxHeight) {
            updateHeaderOffset(-maxHeight)
        }
    }

    private fun calculateHeaderOffset(delta: Float, currentOffset: Float, maxHeight: Float): Float {
        return (currentOffset + delta).coerceIn(-maxHeight, 0f)
    }

    private fun updateHeaderOffset(offset: Float) {
        _scrollState.update { it.copy(headerOffset = offset) }
    }
}