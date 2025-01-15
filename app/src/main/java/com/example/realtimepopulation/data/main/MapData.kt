package com.example.realtimepopulation.data.main


import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="Map")
data class MapData(
    @Element(name = "SeoulRtd.citydata_ppltn")
    val seoulRtd: SeoulRtd
)

@Xml(name="SeoulRtd.citydata_ppltn")
data class SeoulRtd(
    @PropertyElement(name = "AREA_NM")
    val areaName: String? = null,

    @PropertyElement(name = "AREA_CONGEST_LVL")
    val areaCongestLvl: String? = null,

    @PropertyElement(name = "AREA_CONGEST_MSG")
    val areaCongestMsg: String? = null,
)