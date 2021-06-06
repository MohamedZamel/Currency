package com.mohamedzamel.currency.features.convertRates.domain.usecases

import com.mohamedzamel.currency.features.showAllCurrencies.data.CurrencyRepository
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate

class ConvertRatesUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(value: Double, rate: Rate): Double =
        currencyRepository.calculateNewRate(value, rate)
}