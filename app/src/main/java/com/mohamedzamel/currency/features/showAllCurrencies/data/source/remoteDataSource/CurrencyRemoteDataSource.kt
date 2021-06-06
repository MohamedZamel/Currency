package com.mohamedzamel.currency.features.showAllCurrencies.data.source.remoteDataSource

import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyDataSource
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.ErrorResponse
import com.mohamedzamel.currency.utils.api.CurrencyService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CurrencyRemoteDataSource :
    CurrencyDataSource, KoinComponent {
    //  private val currencyService: CurrencyService by inject()val myService : MyService by inject()
    private val currencyService: CurrencyService by inject()

    override suspend fun getCurrencies(): NetworkResponse<Currencies, ErrorResponse> {
        return currencyService.getCurrencies()
    }
}