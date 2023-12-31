package com.fjr619.currencyconverter.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fjr619.currencyconverter.R
import com.fjr619.currencyconverter.presenter.components.CardCurrencyFrom
import com.fjr619.currencyconverter.presenter.components.CardCurrencyTo
import com.fjr619.currencyconverter.presenter.components.KeyboardButton

@Composable
fun MainContentLandscape(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit,
    keys: List<String>,
    onSelectFromCurrency: () -> Unit,
    onSelectToCurrency: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        ) {
            Column {
                //card currency from
                CardCurrencyFrom(state = state, onDropDownIconClicked = onSelectFromCurrency)

                Spacer(modifier = Modifier.height(12.dp))

                //card currency to
                CardCurrencyTo(state = state, onDropDownIconClicked = onSelectToCurrency)
            }

            //currency swap
            IconButton(
                onClick = {
                    onEvent(MainScreenEvent.SwapIconClicked)
                },
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_swap),
                    contentDescription = "Swap Currency",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
                )
            }
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 32.dp).weight(1f),
            columns = GridCells.Fixed(3)
        ) {
            items(keys,
                span = { key ->
                    val spanCount = if (key == "C") 2 else 1
                    GridItemSpan(spanCount)
                }
            ) { key ->
                KeyboardButton(
                    modifier = Modifier.height(85.dp),
                    key = key,
                    backgroundColor = if (key == "C") MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surfaceVariant,
                    onClick = {
                        if (key != ".") {
                            onEvent(MainScreenEvent.NumberButtonClicked(key))
                        }
                    }
                )
            }
        }
    }
}