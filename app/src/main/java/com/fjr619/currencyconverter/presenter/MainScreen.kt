package com.fjr619.currencyconverter.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.currencyconverter.R
import com.fjr619.currencyconverter.presenter.components.CardCurrencyFrom
import com.fjr619.currencyconverter.presenter.components.CardCurrencyTo
import com.fjr619.currencyconverter.presenter.components.CurrencyRow
import com.fjr619.currencyconverter.presenter.components.KeyboardButton

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
) {

    val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "C")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Currency Converter",
            fontFamily = FontFamily.Default,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                //card currency from
                CardCurrencyFrom(state = state)

                Spacer(modifier = Modifier.height(12.dp))

                //card currency to
                CardCurrencyTo(state = state)
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
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 30.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(keys) { key ->
                KeyboardButton(
                    modifier = Modifier.aspectRatio(1f),
                    key = key,
                    backgroundColor = if (key == "C") MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.surfaceVariant,
                    onClick = {
                        onEvent(MainScreenEvent.NumberButtonClicked(key))
                    }
                )
            }
        }
    }
}