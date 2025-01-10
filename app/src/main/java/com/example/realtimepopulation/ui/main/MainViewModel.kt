package com.example.realtimepopulation.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimepopulation.data.main.LocationData
import com.example.realtimepopulation.di.api.SeoulAreaApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val seoulAreaApiService: SeoulAreaApiService
): ViewModel() {
    private val _seoulLocationData = MutableStateFlow<List<LocationData>>(emptyList())
    val seoulLocationData: StateFlow<List<LocationData>> get() = _seoulLocationData

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    val areaTypes = listOf("관광특구", "고궁·문화유산", "인구밀집지역", "발달상권", "공원")

    private val _selectChip = MutableStateFlow<String?>(areaTypes.first())
    val selectChip: StateFlow<String?> get() = _selectChip

    fun readSeoulAreasFromExcel(context: Context) {
        val inputStream: InputStream = context.assets.open("seoul_important_regions.xlsx")
        val workbook = WorkbookFactory.create(inputStream)
        val sheet: Sheet = workbook.getSheetAt(0)

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
                        }.jpg"
                    )
                )
            }
        }
        _seoulLocationData.value = temp
    }

    fun setQueryText(text: String) {
        _searchQuery.value = text
    }

    fun setSelectChip(text: String) {
        _selectChip.value = text
    }

    fun getAreaPopulationData() {
        viewModelScope.launch {
            kotlin.runCatching {
                val response = seoulAreaApiService.getPopulationData("광화문·덕수궁")
                if (response.isSuccessful) {
                    // Log the raw response to check if it's as expected
                    Log.d("test2", response.body()?.toString() ?: "No response body")

                    // Log the parsed data to check if TikXml maps it correctly
                    val areaPopulationData = response.body()
                    Log.d("test3", areaPopulationData.toString())
                } else {
                    Log.d("testError", "Response error: ${response.code()}")
                }
            }.onFailure {
                Log.d("test3", it.toString()) // Log any failure
            }
        }
    }
}