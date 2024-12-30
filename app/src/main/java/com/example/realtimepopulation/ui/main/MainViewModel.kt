package com.example.realtimepopulation.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

class MainViewModel : ViewModel() {
    private val _seoulAreaNames = MutableLiveData<List<String>>()
    val seoulAreaNames: LiveData<List<String>> get() = _seoulAreaNames

    fun readSeoulAreasFromExcel(context: Context) {
        val inputStream: InputStream = context.assets.open("seoul_important_regions.xlsx")
        val workbook = WorkbookFactory.create(inputStream)
        val sheet: Sheet = workbook.getSheetAt(0)

        val temp = mutableListOf<String>()

        for (rowIndex in 1 until sheet.physicalNumberOfRows) {
            sheet.getRow(rowIndex)?.getCell(3)?.toString()?.let {
                temp.add(it)
            }
        }

        _seoulAreaNames.value = temp
    }
}