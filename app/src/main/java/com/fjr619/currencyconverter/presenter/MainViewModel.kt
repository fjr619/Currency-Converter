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

    private fun getCurrencyRatesList() {
        Log.e("TAG", "init getCurrencyRatesList")
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