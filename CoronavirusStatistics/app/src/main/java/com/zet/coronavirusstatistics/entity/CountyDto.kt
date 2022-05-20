package com.zet.coronavirusstatistics.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class CountyDto(
    @Json(name = "Country")
    val country: String, // Wallis and Futuna Islands
    @Json(name = "Slug")
    val slug: String, // wallis-and-futuna-islands
    @Json(name = "ISO2")
    val iSO2: String // WF
)