package com.mohamedzamel.currency.utils.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.mohamedzamel.currency.BuildConfig
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Currencies
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points
 */
interface CurrencyService {
    @GET("api/latest?")
    suspend fun getCurrencies(@Query("access_key") accessKey: String = BuildConfig.Access_key): NetworkResponse<Currencies, ErrorResponse>
}
