package com.zet.coronavirusstatistics.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class CountryDetailDto(
    @Json(name = "ID")
    val iD: String, // 151426fa-82d7-4a05-88b8-c89c738d18b2
    @Json(name = "Country")
    val country: String, // Benin
    @Json(name = "CountryCode")
    val countryCode: String, // BJ
    @Json(name = "Province")
    val province: String,
    @Json(name = "City")
    val city: String,
    @Json(name = "CityCode")
    val cityCode: String,
    @Json(name = "Lat")
    val lat: String, // 9.31
    @Json(name = "Lon")
    val lon: String, // 2.32
    @Json(name = "Confirmed")
    val confirmed: Int, // 0
    @Json(name = "Deaths")
    val deaths: Int, // 0
    @Json(name = "Recovered")
    val recovered: Int, // 0
    @Json(name = "Active")
    val active: Int, // 0
    @Json(name = "Date")
    val date: String // 2020-01-22T00:00:00Z
)