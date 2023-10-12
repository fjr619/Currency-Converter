package com.fjr619.currencyconverter.data.repository

import com.fjr619.currencyconverter.data.local.CurrencyRateDao
import com.fjr619.currencyconverter.data.local.entity.toCurrencyRate
import com.fjr619.currencyconverter.data.local.entity.toCurrencyRateEntity
import com.fjr619.currencyconverter.data.remote.CurrencyApi
import com.fjr619.currencyconverter.data.remote.dto.toCurrencyRates
import com.fjr619.currencyconverter.domain.model.CurrencyRate
import com.fjr619.currencyconverter.domain.model.RequestState
import com.fjr619.currencyconverter.domain.repository.ICurrencyRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRateRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val dao: CurrencyRateDao
) : ICurrencyRateRepository {
    override fun getCurrencyRatesList(): Flow<RequestState<List<CurrencyRate>>> = flow {
        emit(RequestState.Loading)
        val localCurrencyRates = getLocalCurrencyRates()
        if (localCurrencyRates.isNotEmpty()) {
            emit(RequestState.Success(localCurrencyRates))

            val newRates = getRemoteCurrencyRates()
            updateLocalCurrencyRates(newRates)
        } else {
            try {
                val newRates = getRemoteCurrencyRates()
                updateLocalCurrencyRates(newRates)
                emit(RequestState.Success(newRates))
            } catch (e: Exception) {
                emit(RequestState.Error(e))
            }
        }
    }

    private suspend fun getLocalCurrencyRates(): List<CurrencyRate> {
        return dao.getAllCurrencyRates().map { it.toCurrencyRate() }
    }

    private suspend fun getRemoteCurrencyRates(): List<CurrencyRate> {
        val response = api.getLatestRates()
        return response.toCurrencyRates()
    }

    private suspend fun updateLocalCurrencyRates(currencyRates: List<CurrencyRate>) {
        dao.upsertAll(currencyRates.map { it.toCurrencyRateEntity() })
    }
}