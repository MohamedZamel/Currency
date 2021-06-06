package com.mohamedzamel.currency.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 *adding bindingAdapter to [TextView] to allow it to use
 */
@BindingAdapter("HandyRateText")
fun bindSpannableText(textView: TextView, rate: Double) {
    val text = rate.round(2).toString()
    textView.text = text
}

