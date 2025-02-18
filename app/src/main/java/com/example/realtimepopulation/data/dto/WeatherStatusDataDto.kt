import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

/** 최상위 XML 구조 **/
@Xml(name = "SeoulRtd.citydata")
data class WeatherStatusDataDto(
    @Element(name = "CITYDATA")
    val cityData: CityData?
)

/** CITYDATA 내부 구조 **/
@Xml(name = "CITYDATA")
data class CityData(
    @Element(name = "WEATHER_STTS")
    val weatherSttsWrapper: WeatherSttsWrapper?
)

/** WEATHER_STTS가 중첩되어 있는 부분 처리 **/
@Xml(name = "WEATHER_STTS")
data class WeatherSttsWrapper(
    @Element(name = "WEATHER_STTS")
    val weatherStts: WeatherStts?
)

/** 실제 날씨 데이터 매핑 **/
@Xml(name = "WEATHER_STTS")
data class WeatherStts(
    @PropertyElement(name = "WEATHER_TIME") val weatherTime: String?,
    @PropertyElement(name = "TEMP") val temp: String?,
    @PropertyElement(name = "SENSIBLE_TEMP") val sensibleTemp: String?,
    @PropertyElement(name = "MAX_TEMP") val maxTemp: String?,
    @PropertyElement(name = "MIN_TEMP") val minTemp: String?,
    @PropertyElement(name = "HUMIDITY") val humidity: String?,
    @PropertyElement(name = "WIND_DIRCT") val windDirection: String?,
    @PropertyElement(name = "WIND_SPD") val windSpeed: String?,
    @PropertyElement(name = "PRECIPITATION") val precipitation: String?,
    @PropertyElement(name = "PRECPT_TYPE") val precptType: String?,
    @PropertyElement(name = "PCP_MSG") val pcpMsg: String?,
    @PropertyElement(name = "SUNRISE") val sunrise: String?,
    @PropertyElement(name = "SUNSET") val sunset: String?,
    @PropertyElement(name = "UV_INDEX_LVL") val uvIndexLvl: String?,
    @PropertyElement(name = "UV_INDEX") val uvIndex: String?,
    @PropertyElement(name = "UV_MSG") val uvMsg: String?,
    @PropertyElement(name = "PM25_INDEX") val pm25Index: String?,
    @PropertyElement(name = "PM25") val pm25: String?,
    @PropertyElement(name = "PM10_INDEX") val pm10Index: String?,
    @PropertyElement(name = "PM10") val pm10: String?,
    @PropertyElement(name = "AIR_IDX") val airIndex: String?,
    @PropertyElement(name = "AIR_IDX_MVL") val airIdxMvl: String?,
    @PropertyElement(name = "AIR_IDX_MAIN") val airIdxMain: String?,
    @PropertyElement(name = "AIR_MSG") val airMsg: String?,

    @Element(name = "FCST24HOURS")
    val forecastWrapper: ForecastWrapper?
)

/** FCST24HOURS가 중첩된 구조 처리 **/
@Xml(name = "FCST24HOURS")
data class ForecastWrapper(
    @Element(name = "FCST24HOURS")
    val forecasts: List<WeatherForecastData>?
)

/** 날씨 예보 데이터 **/
@Xml(name = "FCST24HOURS")
data class WeatherForecastData(
    @PropertyElement(name = "FCST_DT") val fcstDt: String?,
    @PropertyElement(name = "TEMP") val temp: String?,
    @PropertyElement(name = "PRECIPITATION") val precipitation: String?,
    @PropertyElement(name = "PRECPT_TYPE") val precptType: String?,
    @PropertyElement(name = "RAIN_CHANCE") val rainChance: String?,
    @PropertyElement(name = "SKY_STTS") val skyStts: String?
)
