package com.example.realtimepopulation.data.dto


import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="Map")
data class MapDataDto(
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

    @PropertyElement(name = "AREA_PPLTN_MIN")
    val minCount: String? = null,

    @PropertyElement(name = "AREA_PPLTN_MAX")
    val maxCount: String? = null,

    @Element(name = "FCST_PPLTN")
    val fcstPpltn: FcstPpltn
)

@Xml(name="FCST_PPLTN")
data class FcstPpltn(
    @Element(name = "FCST_PPLTN")
    val fcstPpltnList: List<FcstPpltnItem>
)

@Xml(name = "FCST_PPLTN")
data class FcstPpltnItem(
    @PropertyElement(name = "FCST_TIME")
    val fcstTime: String? = null,

    @PropertyElement(name = "FCST_CONGEST_LVL")
    val fcstCongestLvl: String? = null,

    @PropertyElement(name = "FCST_PPLTN_MIN")
    val fcstPpltnMin: Int? = null,

    @PropertyElement(name = "FCST_PPLTN_MAX")
    val fcstPpltnMax: Int? = null
)