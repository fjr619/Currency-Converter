package com.fjr619.currencyconverter.data.remote.dto

import com.fjr619.currencyconverter.domain.model.CurrencyRate

data class CurrencyDataDto(
    val AUD: Double,
    val BGN: Double,
    val BRL: Double,
    val CAD: Double,
    val CHF: Double,
    val CNY: Double,
    val CZK: Double,
    val DKK: Double,
    val EUR: Double,
    val GBP: Double,
    val HKD: Double,
    val HRK: Double,
    val HUF: Double,
    val IDR: Double,
    val ILS: Double,
    val INR: Double,
    val ISK: Double,
    val JPY: Double,
    val KRW: Double,
    val MXN: Double,
    val MYR: Double,
    val NOK: Double,
    val NZD: Double,
    val PHP: Double,
    val PLN: Double,
    val RON: Double,
    val RUB: Double,
    val SEK: Double,
    val SGD: Double,
    val THB: Double,
    val TRY: Double,
    val USD: Double,
    val ZAR: Double
)

data class CurrencyDto(
    val data: CurrencyDataDto
)

fun CurrencyDto.toCurrencyRates(): List<CurrencyRate> {
    val currencyData = this.data
    return listOf(
        CurrencyRate("INR", "Indian Rupee", currencyData.INR),
        CurrencyRate("EUR", "Euro", currencyData.EUR),
        CurrencyRate("USD", "US Dollar", currencyData.USD),
        CurrencyRate("JPY", "Japanese Yen", currencyData.JPY),
        CurrencyRate("BGN", "Bulgarian Lev", currencyData.BGN),
        CurrencyRate("CZK", "Czech Republic Koruna", currencyData.CZK),
        CurrencyRate("DKK", "Danish Krone", currencyData.DKK),
        CurrencyRate("GBP", "British Pound Sterling", currencyData.GBP),
        CurrencyRate("HUF", "Hungarian Forint", currencyData.HUF),
        CurrencyRate("PLN", "Polish Zloty", currencyData.PLN),
        CurrencyRate("RON", "Romanian Leu", currencyData.RON),
        CurrencyRate("SEK", "Swedish Krona", currencyData.SEK),
        CurrencyRate("CHF", "Swiss Franc", currencyData.CHF),
        CurrencyRate("ISK", "Icelandic Króna", currencyData.ISK),
        CurrencyRate("NOK", "Norwegian Krone", currencyData.NOK),
        CurrencyRate("HRK", "Croatian Kuna", currencyData.HRK),
        CurrencyRate("RUB", "Russian Ruble", currencyData.RUB),
        CurrencyRate("TRY", "Turkish Lira", currencyData.TRY),
        CurrencyRate("AUD", "Australian Dollar", currencyData.AUD),
        CurrencyRate("BRL", "Brazilian Real", currencyData.BRL),
        CurrencyRate("CAD", "Canadian Dollar", currencyData.CAD),
        CurrencyRate("CNY", "Chinese Yuan", currencyData.CNY),
        CurrencyRate("HKD", "Hong Kong Dollar", currencyData.HKD),
        CurrencyRate("IDR", "Indonesian Rupiah", currencyData.IDR),
        CurrencyRate("ILS", "Israeli New Sheqel", currencyData.ILS),
        CurrencyRate("KRW", "South Korean Won", currencyData.KRW),
        CurrencyRate("MXN", "Mexican Peso", currencyData.MXN),
        CurrencyRate("MYR", "Malaysian Ringgit", currencyData.MYR),
        CurrencyRate("NZD", "New Zealand Dollar", currencyData.NZD),
        CurrencyRate("PHP", "Philippine Peso", currencyData.PHP),
        CurrencyRate("SGD", "Singapore Dollar", currencyData.SGD),
        CurrencyRate("THB", "Thai Baht", currencyData.THB),
        CurrencyRate("ZAR", "South African Rand", currencyData.ZAR)
    )
}