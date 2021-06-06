package com.mohamedzamel.currency.features.showAllCurrencies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohamedzamel.currency.R
import com.mohamedzamel.currency.databinding.FragmentShowCurrenciesListBinding
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate
import com.mohamedzamel.currency.utils.ui.getViewModelFactory

/**
 * A [CurrenciesListFragment] to Show list of Currencies
 */
class CurrenciesListFragment :
    Fragment() {
    private val viewModel by viewModels<CurrencyListViewModel> { getViewModelFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShowCurrenciesListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.mainLayout.showLoading()
        viewModel.fetchCurrencies()
        addLoadingObserver(binding)
        showRatesList(binding)
        addErrorObserver(binding)
        addSwipeToRefresh(binding)



        return binding.root
    }

    private fun addLoadingObserver(binding: FragmentShowCurrenciesListBinding) {
        viewModel.dataLoading.observe(
            viewLifecycleOwner,
            {
                if (it) {
                    binding.mainLayout.showLoading()
                } else {
                    binding.mainLayout.showContent()
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        )
    }
    private fun addSwipeToRefresh(binding: FragmentShowCurrenciesListBinding) {
       binding.swipeRefresh.setOnRefreshListener { viewModel.fetchCurrencies() }
    }

    private fun addErrorObserver(binding: FragmentShowCurrenciesListBinding) {
        viewModel.errorMessage.observe(
            viewLifecycleOwner,
            {
                binding.mainLayout.showError(
                    R.drawable.ic_baseline_error_outline_24,
                    getString(R.string.something_went_wrong_label),
                    it,
                    getString(
                        R.string.retry_label
                    )
                ) { viewModel.fetchCurrencies() }

            }
        )
    }

    /**
     * show base currencies list when when needed
     */
    private fun showRatesList(binding: FragmentShowCurrenciesListBinding) {

        val adapter = CurrenciesAdapter()
        binding.currencyList.adapter = adapter
        viewModel.currencies.observe(viewLifecycleOwner) {
            val listOfRate =
                it?.rates?.toList()?.map { item -> Rate(item.first, item.second) }
            adapter.submitList(listOfRate)
            binding.mainLayout.showContent()
        }
    }
}
