package com.mohamedzamel.currency.features.showAllCurrencies.domain.usecases

import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyRepository
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.ErrorResponse

class GetAllCurrenciesRatesUseCase(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(): NetworkResponse<Currencies, ErrorResponse> =
        currencyRepository.getCurrencies()
}