package com.fjr619.currencyconverter.presenter

sealed class MainScreenEvent {
    object FromCurrencySelect: MainScreenEvent()
    object ToCurrencySelect: MainScreenEvent()
    object SwapIconClicked: MainScreenEvent()
    data class BottomSheetItemClicked(val value: String): MainScreenEvent()
    data class NumberButtonClicked(val value: String): MainScreenEvent()
}