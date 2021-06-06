package com.mohamedzamel.currency.features.showAllCurrencies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies

import com.mohamedzamel.currency.features.showAllCurrencies.domain.usecases.GetAllCurrenciesRatesUseCase

import kotlinx.coroutines.launch

class CurrencyListViewModel(private var getAllCurrenciesRatesUseCase: GetAllCurrenciesRatesUseCase) :
    ViewModel() {

    fun fetchCurrencies() {
        if (_dataLoading.value == true) {
            return
        }
        _dataLoading.value = true

        viewModelScope.launch {
            getAllCurrenciesRatesUseCase().let { result ->
                when (result) {
                    is NetworkResponse.Success -> {
                        // Handle successful response
                        _currencies.postValue(result.body)
                    }
                    is NetworkResponse.ServerError -> {
                        // Handle server error
                        _error.postValue(true)
                        _errorMessage.postValue(result.body?.message)
                    }
                    is NetworkResponse.NetworkError -> {
                        // Handle network error
                        _error.postValue(true)
                        _errorMessage.postValue(result.error.message)
                    }
                    is NetworkResponse.UnknownError -> {
                        // Handle other errors
                        _error.postValue(true)
                        _errorMessage.postValue(result.error.message)
                    }
                }
                _dataLoading.value = false
            }
        }
    }

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _currencies = MutableLiveData<Currencies>()
    val currencies: LiveData<Currencies> = _currencies
}
