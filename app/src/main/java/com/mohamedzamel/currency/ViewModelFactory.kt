package com.mohamedzamel.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamedzamel.currency.features.convertRates.domain.usecases.ConvertRatesUseCase
import com.mohamedzamel.currency.features.convertRates.ui.ConvertRateViewModel
import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyRepository
import com.mohamedzamel.currency.features.showAllCurrencies.domain.usecases.GetAllCurrenciesRatesUseCase
import com.mohamedzamel.currency.features.showAllCurrencies.ui.CurrencyListViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CurrencyListViewModel::class.java) ->
                    CurrencyListViewModel(
                        GetAllCurrenciesRatesUseCase(currencyRepository)
                    )
                isAssignableFrom(ConvertRateViewModel::class.java) ->
                    ConvertRateViewModel(
                        ConvertRatesUseCase(currencyRepository)
                    )
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
