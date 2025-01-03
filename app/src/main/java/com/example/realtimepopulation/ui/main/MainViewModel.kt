package com.example.realtimepopulation.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.realtimepopulation.data.main.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

class MainViewModel : ViewModel() {
    private val _seoulLocationData = MutableStateFlow<List<LocationData>>(emptyList())
    val seoulLocationData: StateFlow<List<LocationData>> get() = _seoulLocationData

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> get() = _searchQuery

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
                        areaName = row.getCell(3)?.toString() ?: "",
                        lat = row.getCell(4)?.toString()?.toDoubleOrNull() ?: 0.0,
                        long = row.getCell(5)?.toString()?.toDoubleOrNull() ?: 0.0
                    )
                )
            }
        }
        _seoulLocationData.value = temp
    }

    fun setQueryText(text: String) {
        _searchQuery.value = text
    }
}