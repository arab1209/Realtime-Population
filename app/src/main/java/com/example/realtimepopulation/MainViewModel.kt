package com.example.realtimepopulation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

class MainViewModel : ViewModel() {
    fun readSeoulAreasFromExcel(context: Context) {
        val inputStream: InputStream = context.assets.open("seoul_important_regions.xlsx")
        val workbook = WorkbookFactory.create(inputStream)
        val sheet: Sheet = workbook.getSheetAt(0)

        for (rowIndex in 1 until sheet.physicalNumberOfRows) {
            Log.d("엑셀 데이터", sheet.getRow(rowIndex)?.getCell(3).toString())
        }
    }
}