package com.mohamedzamel.currency.utils.api

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.mohamedzamel.currency.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 10 // 10 seconds
private const val READ_TIMEOUT = 2 // 2 seconds
private const val WRITE_TIMEOUT = 2 // 2 seconds

val networkModule = module {

    single { providesHttpLogging() }
    single { providesHttpClient(get()) }
    single { providesRetrofit(get()) }
    single { providesCurrencyService(get()) }
}

internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()
}

internal fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    // add request time out
    builder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
    // Add logging into
    builder.interceptors().add(httpLoggingInterceptor)
    return builder.build()
}

internal fun providesHttpLogging(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

internal fun providesCurrencyService(retrofit: Retrofit): CurrencyService {
    return retrofit.create(CurrencyService::class.java)
}

