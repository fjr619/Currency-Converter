package com.fjr619.currencyconverter.presenter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.currencyconverter.presenter.MainScreenState

@Composable
fun ColumnScope.CardCurrencyFrom(
    state: MainScreenState
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.End
        ) {
            CurrencyRow(
                modifier = Modifier.fillMaxWidth(),
                currencyCode = state.fromCurrencyCode,
                currencyName = state.currencyRates[state.fromCurrencyCode]?.name ?: "",
                onDropDownIconClicked = {
                    //TODO
                }
            )

            Text(
                text = state.fromCurrencyValue,
                fontSize = 40.sp,
            )
        }
    }
}