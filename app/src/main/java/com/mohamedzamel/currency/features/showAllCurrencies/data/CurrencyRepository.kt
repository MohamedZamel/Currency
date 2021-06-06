package com.mohamedzamel.currency.features.showAllCurrencies.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.ErrorResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate

interface CurrencyRepository {
    suspend fun getCurrencies(): NetworkResponse<Currencies, ErrorResponse>
    fun calculateNewRate(value: Double, rateToConvert: Rate): Double
}