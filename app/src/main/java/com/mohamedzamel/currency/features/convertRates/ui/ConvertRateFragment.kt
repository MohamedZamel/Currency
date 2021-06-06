package com.mohamedzamel.currency.features.convertRates.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mohamedzamel.currency.databinding.FragmentConvertRatesBinding
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate
import com.mohamedzamel.currency.utils.ui.getViewModelFactory

/**
 * A [ConvertRateFragment] to Show list of Currencies
 */
class ConvertRateFragment :
    Fragment() {
    private val viewModel by viewModels<ConvertRateViewModel> { getViewModelFactory() }
    private val args: ConvertRateFragmentArgs by navArgs()
    private lateinit var rateToConvert: Rate
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentConvertRatesBinding.inflate(inflater, container, false)
        context ?: return binding.root
        rateToConvert = args.rate
        // binding.mainLayout.showLoading()
        addCurrencyCalculationListener(binding, rateToConvert)
        resetBaseValueToOne(binding.baseCurrencyValueEdittext)
        binding.rate = rateToConvert




        return binding.root
    }

    /**
     * calculate the new rate based on the [rate]
     */
    private fun addCurrencyCalculationListener(binding: FragmentConvertRatesBinding, rate: Rate) {

        binding.baseCurrencyValueEdittext.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val baseRateInput = textView.text.toString()
                if (baseRateInput.isNotEmpty()) {
                    viewModel.calculateValueByRate(baseRateInput.toDouble(), rate)
                } else {
                    resetBaseValueToOne(binding.baseCurrencyValueEdittext)
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false

        }
        viewModel.newRate.observe(viewLifecycleOwner, { result ->
            binding.currencyValueTextView.text = result.toString()

        })
    }

    private fun resetBaseValueToOne(editText: EditText) {
        editText.setText(1.toString())
        editText.setSelection(editText.text.toString().length)
    }
}
