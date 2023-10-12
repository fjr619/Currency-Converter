package com.fjr619.currencyconverter.presenter

import com.fjr619.currencyconverter.domain.model.CurrencyRate

data class MainScreenState(
    val fromCurrencyCode: String = "IDR",
    val toCurrencyCode: String = "USD",
    val fromCurrencyValue: String = "0.00",
    val toCurrencyValue: String = "0.00",
    val selection: SelectionState = SelectionState.FROM,
    val currencyRates: Map<String, CurrencyRate> = emptyMap(),
    val error: String? = null,
    val loading: Boolean = true
)

enum class SelectionState {
    FROM,
    TO
}