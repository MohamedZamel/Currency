package com.mohamedzamel.currency.features.convertRates.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamedzamel.currency.features.convertRates.domain.usecases.ConvertRatesUseCase
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate

class ConvertRateViewModel(private var calculateRateUseCase: ConvertRatesUseCase) :
    ViewModel() {

    fun calculateValueByRate(value: Double, rate: Rate) {
        _newRate.postValue(calculateRateUseCase.invoke(value, rate))
    }

    private val _newRate = MutableLiveData<Double>()
    val newRate: LiveData<Double> = _newRate
}
