package com.zet.coronavirusstatistics.framework.network.repository

import com.zet.coronavirusstatistics.di.NetworkModule
import com.zet.coronavirusstatistics.entity.CountryDetailDto
import com.zet.coronavirusstatistics.entity.CountyDto
import com.zet.coronavirusstatistics.framework.network.api.CoronaApi
import com.zet.coronavirusstatistics.utils.ApiError
import com.zet.coronavirusstatistics.utils.ApiException
import com.zet.coronavirusstatistics.utils.ApiResult
import com.zet.coronavirusstatistics.utils.ApiSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoronaRepository(
    private val api: CoronaApi = NetworkModule.provideCoronaApi(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    companion object {
        fun getInstance() = CoronaRepository()
    }

    suspend fun getCountries(): ApiResult<List<CountyDto>> {
        return withContext(dispatcher) {
            try {
                val response = api.getCountryList()
                val body = response.body()
                if (response.isSuccessful && body != null)
                    ApiSuccess(body)
                else
                    ApiError(code = response.code(), message = response.errorBody()?.string() ?: "")
            } catch (e: Exception) {
                ApiException(e)
            }
        }
    }

    suspend fun getDetailCounty(country: String): ApiResult<CountryDetailDto> {
        return withContext(dispatcher) {
            try {
                val response = api.getDetailCounty(country)
                val body = response.body()
                if (response.isSuccessful && body != null)
                    ApiSuccess(body)
                else
                    ApiError(code = response.code(), message = response.errorBody()?.string() ?: "")
            } catch (e: Exception) {
                ApiException(e)
            }
        }
    }

}