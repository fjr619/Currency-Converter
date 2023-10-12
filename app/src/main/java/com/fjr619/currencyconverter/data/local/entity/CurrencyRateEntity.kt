package com.fjr619.currencyconverter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fjr619.currencyconverter.domain.model.CurrencyRate

@Entity
data class CurrencyRateEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val rate: Double
)

fun CurrencyRateEntity.toCurrencyRate(): CurrencyRate {
    return CurrencyRate(
        code = code,
        name = name,
        rate = rate
    )
}

fun CurrencyRate.toCurrencyRateEntity(): CurrencyRateEntity {
    return CurrencyRateEntity(
        code = code,
        name = name,
        rate = rate
    )
}