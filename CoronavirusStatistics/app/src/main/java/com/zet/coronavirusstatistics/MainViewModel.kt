package com.zet.coronavirusstatistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zet.coronavirusstatistics.entity.CountryDetailDto
import com.zet.coronavirusstatistics.entity.CountyDto
import com.zet.coronavirusstatistics.framework.network.repository.CoronaRepository
import com.zet.coronavirusstatistics.utils.ApiError
import com.zet.coronavirusstatistics.utils.ApiException
import com.zet.coronavirusstatistics.utils.ApiSuccess
import com.zet.coronavirusstatistics.utils.string
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

sealed interface UiResult<T : Any>

class UiSuccess<T : Any>(val data: T) : UiResult<T>
class UiError<T : Any>(val message: String?) : UiResult<T>
class UiLoading<T : Any>() : UiResult<T>
class UiEmpty<T : Any>() : UiResult<T>

class MainViewModel(
    private val repository: CoronaRepository = CoronaRepository.getInstance()
) : ViewModel() {

    private val _counties = MutableStateFlow<UiResult<List<CountyDto>>>(UiEmpty())
    val countries get() = _counties.asStateFlow()

    private val _detailCountry = MutableStateFlow<UiResult<CountryDetailDto>>(UiEmpty())
    val detailCountry get() = _detailCountry.asStateFlow()

    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch {
            _counties.emit(UiLoading())
            val result = repository.getCountries()
            Timber.d("ResultD: ${result.string()}")
            when (result) {
                is ApiError -> {
                    _counties.emit(UiError(result.message ?: ""))
                }
                is ApiException -> {
                    _counties.emit(UiError(result.e.message ?: ""))
                }
                is ApiSuccess -> {
                    _counties.emit(UiSuccess(result.data))
                }
            }
        }
    }

    fun getDetailCountry(country: String) {
        viewModelScope.launch {
            _detailCountry.emit(UiLoading())
            val result = repository.getDetailCounty(country)
            Timber.d("ResultD: ${result.string()}")
            when (result) {
                is ApiError -> {
                    _detailCountry.emit(UiError(result.message ?: ""))
                }
                is ApiException -> {
                    _detailCountry.emit(UiError(result.e.message ?: ""))
                }
                is ApiSuccess -> {
                    _detailCountry.emit(UiSuccess(result.data))
                }
            }
        }
    }

}