package com.example.realtimepopulation.data.repository

import android.content.Context
import com.example.realtimepopulation.domain.model.main.LocationData
import com.example.realtimepopulation.domain.repository.SeoulLocationRepository
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream
import java.net.URLEncoder
import javax.inject.Inject

class SeoulLocationRepositoryImpl @Inject constructor(
    private val context: Context,
) : SeoulLocationRepository {
    override fun getSeoulLocationData(): List<LocationData> {
        val inputStream = context.assets.open("seoul_important_regions.xlsx")
        return parseExcel(inputStream)
    }

    private fun parseExcel(inputStream: InputStream): List<LocationData> {
        val sheet = WorkbookFactory.create(inputStream).getSheetAt(0)
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
                        region = row.getCell(7)?.toString() ?: ""
                    )
                )
            }
        }
        return temp
    }
}