package com.mohamedzamel.currency.features.showAllCurrencies.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.ErrorResponse

interface CurrencyDataSource {
    suspend fun getCurrencies(): NetworkResponse<Currencies, ErrorResponse>
}