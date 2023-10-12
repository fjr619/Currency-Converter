package com.fjr619.currencyconverter.presenter.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.currencyconverter.presenter.MainScreenState
import com.fjr619.currencyconverter.util.CurrencyAmountInputVisualTransformation

@Composable
fun ColumnScope.CardCurrencyFrom(
    state: MainScreenState,
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

            TextField(
                enabled = false,
                value = state.fromCurrencyValue,
                onValueChange = {},
                visualTransformation = CurrencyAmountInputVisualTransformation(
                    fixedCursorAtTheEnd = true
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledTextColor = MaterialTheme.colorScheme.onBackground
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 35.sp, textAlign = TextAlign.End)

            )
        }
    }
}