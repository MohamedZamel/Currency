package com.mohamedzamel.currency.features.showAllCurrencies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamedzamel.currency.databinding.CurrencyListItemBinding
import com.mohamedzamel.currency.features.showAllCurrencies.data.entities.Rate

/**
 * Adapter for the [RecyclerView] in [CurrenciesListFragment].
 */
class CurrenciesAdapter : ListAdapter<Rate, RecyclerView.ViewHolder>(RateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrenciesViewHolder(
            CurrencyListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rateItem = getItem(position)
        (holder as CurrenciesViewHolder).bind(rateItem)
    }

    class CurrenciesViewHolder(
        private val binding: CurrencyListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.rate?.let { rate ->
                    navigateToCurrencyConverter(rate, it)
                }
            }
        }

        private fun navigateToCurrencyConverter(
            rate: Rate,
            view: View
        ) {
            val direction =
                CurrenciesListFragmentDirections.actionCurrenciesListFragmentToConvertRatesFragment(
                    rate
                )

            view.findNavController().navigate(direction)
        }

        fun bind(item: Rate) {
            binding.apply {
                rate = item
                executePendingBindings()
            }
        }
    }
}

private class RateDiffCallback : DiffUtil.ItemCallback<Rate>() {

    override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem == newItem
    }
}
