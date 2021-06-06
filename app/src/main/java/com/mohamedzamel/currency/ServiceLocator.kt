package com.mohamedzamel.currency

import androidx.annotation.VisibleForTesting
import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyDataSource
import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyRepository
import com.mohamedzamel.currency.features.showAllCurrencies.data.DefaultCurrencyRepository
import com.mohamedzamel.currency.features.showAllCurrencies.data.source.remoteDataSource.CurrencyRemoteDataSource

/**
 * A Service Locator for the [CurrencyRepository].  with a
 * [CurrencyRemoteDataSource].
 */
object ServiceLocator {

    @Volatile
    var currencyRepository: CurrencyRepository? = null
        @VisibleForTesting set

    fun provideCurrencyRepository(): CurrencyRepository {
        synchronized(this) {
            return currencyRepository ?: currencyRepository ?: createCurrencyRepository()
        }
    }

    private fun createCurrencyRepository(): CurrencyRepository {
        return DefaultCurrencyRepository(createCurrencyRemoteDataSource())
    }

    private fun createCurrencyRemoteDataSource(): CurrencyDataSource {

        return CurrencyRemoteDataSource()
    }
}
