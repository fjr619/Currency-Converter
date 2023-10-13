package com.fjr619.currencyconverter.presenter

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.currencyconverter.R
import com.fjr619.currencyconverter.presenter.components.CardCurrencyFrom
import com.fjr619.currencyconverter.presenter.components.CardCurrencyTo
import com.fjr619.currencyconverter.presenter.components.KeyboardButton
import com.fjr619.currencyconverter.presenter.components.MainContentPortrait

@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
) {

    val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "C")

    val configuration = LocalConfiguration.current

    var orientation by rememberSaveable { mutableIntStateOf(Configuration.ORIENTATION_PORTRAIT) }
    LaunchedEffect(configuration.orientation) {
        orientation = configuration.orientation
    }
    
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        MainContentPortrait(state = state, onEvent = onEvent, keys = keys)
    } else {
        MainContentLandscape(state = state, onEvent = onEvent, keys = keys)
    }

    
}