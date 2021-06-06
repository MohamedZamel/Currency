package com.mohamedzamel.currency.features.showAllCurrencies.data.entities

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Rate(var symbol: String, var rate: Double) : Parcelable