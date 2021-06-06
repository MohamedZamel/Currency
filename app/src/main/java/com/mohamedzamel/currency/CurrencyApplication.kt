package com.mohamedzamel.currency

import android.app.Application
import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyRepository
import com.mohamedzamel.currency.utils.api.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrencyApplication : Application() {
    // Depends on the flavor,

    val currencyRepository: CurrencyRepository
        get() = ServiceLocator.provideCurrencyRepository()

    override fun onCreate() {
        super.onCreate()
        startKoinInjection()
    }

    private fun startKoinInjection() {
        startKoin {
            androidContext(this@CurrencyApplication)
            modules(listOf(networkModule))
        }
    }
}