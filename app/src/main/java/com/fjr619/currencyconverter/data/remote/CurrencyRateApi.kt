package com.fjr619.currencyconverter.data.remote

import com.fjr619.currencyconverter.data.remote.dto.CurrencyDto
import com.fjr619.currencyconverter.util.Util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String = API_KEY
    ): CurrencyDto
}