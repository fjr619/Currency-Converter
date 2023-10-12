package com.fjr619.currencyconverter.domain.repository

import com.fjr619.currencyconverter.domain.model.CurrencyRate
import com.fjr619.currencyconverter.domain.model.RequestState
import kotlinx.coroutines.flow.Flow

interface ICurrencyRateRepository {
    fun getCurrencyRatesList(): Flow<RequestState<List<CurrencyRate>>>
}