package com.mohamedzamel.currency.features.showAllCurrencies.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.ErrorResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultCurrencyRepository(
    private val currencyRemoteDataSource: CurrencyDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CurrencyRepository {
    override suspend fun getCurrencies(): NetworkResponse<Currencies, ErrorResponse> {
        return withContext(ioDispatcher) {

            return@withContext currencyRemoteDataSource.getCurrencies()
        }
    }

    override fun calculateNewRate(value: Double, rateToConvert: Rate): Double {
        return value * rateToConvert.rate
    }
}