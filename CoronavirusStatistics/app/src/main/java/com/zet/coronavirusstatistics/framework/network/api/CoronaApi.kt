package com.zet.coronavirusstatistics.framework.network.api

import com.zet.coronavirusstatistics.entity.CountryDetailDto
import com.zet.coronavirusstatistics.entity.CountyDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoronaApi {

    @GET("countries")
    suspend fun getCountryList(): Response<List<CountyDto>>

    @GET("country/{country}")
    suspend fun getDetailCounty(@Path("country") country: String): Response<CountryDetailDto>

}