package com.fjr619.currencyconverter.presenter

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fjr619.currencyconverter.domain.model.CurrencyRate

@Composable
fun BottomSheetContent(
    onItemClicked: (String) -> Unit,
    currenciesList: List<CurrencyRate>,
    orientation: Int
) {


    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(currenciesList) { currency ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClicked(currency.code) }
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(text = "${currency.code}: ${currency.name}")
                }
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(currenciesList) { currency ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClicked(currency.code) }
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    Text(text = "${currency.code}: ${currency.name}")
                }
            }
        }
    }

}