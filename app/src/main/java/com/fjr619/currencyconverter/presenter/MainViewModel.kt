package com.fjr619.currencyconverter.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.currencyconverter.domain.model.RequestState
import com.fjr619.currencyconverter.domain.repository.ICurrencyRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ICurrencyRateRepository
): ViewModel() {

    private var _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        getCurrencyRatesList()
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.NumberButtonClicked -> {
                updateCurrencyValue(value = event.value)
            }
            else -> {}
        }
    }

    //TODO ganti jadi edittext dan pakai VisualTransformation
    //https://medium.com/@banmarkovic/how-to-create-currency-amount-input-in-android-jetpack-compose-1bd11ba3b629
    private fun updateCurrencyValue(value: String) {
        val currentCurrencyValue = when(state.value.selection) {
            SelectionState.FROM -> state.value.fromCurrencyValue
            SelectionState.TO -> state.value.toCurrencyValue
        }

        val fromCurrencyRate = state.value.currencyRates[state.value.fromCurrencyCode]?.rate ?: 0.0
        val toCurrencyRate = state.value.currencyRates[state.value.toCurrencyCode]?.rate ?: 0.0

        val updatedCurrencyValue = when(value) {
            "C" -> "0"
            else ->
                if (currentCurrencyValue == "0") value else {
                    (currentCurrencyValue + value).run {
                        if (this.length > 12) {
                            this.substring(0,12)
                        } else {
                            this
                        }
                    }

                }
        }

        Log.e("TAG", "$currentCurrencyValue $updatedCurrencyValue")

        val numberFormat = DecimalFormat("#.##")

        when(state.value.selection) {
            SelectionState.FROM -> {

                val fromValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                val toValue = fromValue/ 100 / fromCurrencyRate * toCurrencyRate
                _state.update { mainScreenState ->
                    mainScreenState.copy(
                        fromCurrencyValue = updatedCurrencyValue,
                        toCurrencyValue = (numberFormat.format(toValue).toDouble() * 100).toInt().toString()
                    )
                }
            }
            SelectionState.TO -> {
                val toValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                val fromValue = toValue/ 100 / toCurrencyRate * fromCurrencyRate
                _state.update { mainScreenState ->
                    mainScreenState.copy(
                        fromCurrencyValue = updatedCurrencyValue,
                        toCurrencyValue = (numberFormat.format(fromValue).toDouble() * 100).toInt().toString()
                    )
                }
            }
        }

    }

    private fun getCurrencyRatesList() {
        viewModelScope.launch {
            repository.getCurrencyRatesList()
                .collect { result ->
                    when (result) {
                        is RequestState.Loading -> {
                            _state.update {
                                mainScreenState ->
                                mainScreenState.copy(
                                    loading = true
                                )
                            }

                            Log.e("TAG", "loading")
                        }
                        is RequestState.Success -> {
                            _state.update { mainScreenState ->
                                mainScreenState.copy(
                                    currencyRates = result.data.associateBy { it.code },
                                    error = null,
                                    loading = false
                                )
                            }

                            Log.e("TAG", "sukses rate IDR ${state.value.currencyRates["IDR"]}")
                        }
                        is RequestState.Error -> {
                            _state.update { mainScreenState ->
                                mainScreenState.copy(
                                    currencyRates = emptyMap(),
                                    error = result.error.message,
                                    loading = false
                                )
                            }

                            Log.e("TAG", "Error ${state.value.error}")
                        }
                        else -> {

                        }
                    }
                }
        }
    }
}