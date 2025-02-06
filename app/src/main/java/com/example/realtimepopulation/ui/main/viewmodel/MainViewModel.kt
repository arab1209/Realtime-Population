package com.example.realtimepopulation.ui.main.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.model.main.ScrollStateData
import com.example.realtimepopulation.domain.model.map.MapData
import com.example.realtimepopulation.domain.usecase.GetAreaPopulationDataUseCase
import com.example.realtimepopulation.domain.usecase.GetSeoulLocationDataUseCase
import com.example.realtimepopulation.ui.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSeoulLocationDataUseCase: GetSeoulLocationDataUseCase,
    private val getAreaPopulationDataUseCase: GetAreaPopulationDataUseCase
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
        readSeoulAreasFromExcel()
    }

    fun readSeoulAreasFromExcel() {
        viewModelScope.launch {
            val (seoulLocationData, chipData) = getSeoulLocationDataUseCase(areaTypes.first())
            _seoulLocationData.value = seoulLocationData
            _selectChipData.value = chipData
            Log.d("test", getAreaPopulationDataUseCase(seoulLocationData).toString())
            _populationData.value = getAreaPopulationDataUseCase(seoulLocationData)
        }
    }

    fun setQueryText(text: String) {
        _searchQuery.value = text
    }

    fun setSelectChip(text: String) {
        _selectChip.value = text
        _selectChipData.value = _seoulLocationData.value.filter { it.category == text }
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

    fun calcAreaColor(congestLvl: String): Color {
        return when (congestLvl) {
            "붐빔" -> Color(0xFFFF5675)
            "약간 붐빔" -> Color(0xFFFF9100)
            "보통" -> Color(0xFFFFD232)
            else -> Color(0xFF80E12A)
        }
    }
}