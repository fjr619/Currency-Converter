package com.fjr619.currencyconverter.presenter

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import com.fjr619.currencyconverter.presenter.components.MainContentPortrait
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    onEvent: (MainScreenEvent) -> Unit
) {

    val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "C")

    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var orientation by rememberSaveable { mutableIntStateOf(Configuration.ORIENTATION_PORTRAIT) }
    LaunchedEffect(configuration.orientation) {
        orientation = configuration.orientation
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false },
//            dragHandle = {
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    BottomSheetDefaults.DragHandle()
//                    Text(
//                        text = "Select Currency",
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Divider()
//                }
//            },
            content = {
                BottomSheetContent(
                    orientation = orientation,
                    onItemClicked = { currencyCode ->
                        onEvent(MainScreenEvent.BottomSheetItemClicked(currencyCode))
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) isSheetOpen = false
                        }
                    },
                    currenciesList = state.currencyRates.values.toList()
                )
            }
        )
    }

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        MainContentPortrait(state = state, onEvent = onEvent, keys = keys, onSelectFromCurrency = {

            isSheetOpen = true
            onEvent(MainScreenEvent.FromCurrencySelect)
        }, onSelectToCurrency = {
            isSheetOpen = true
            onEvent(MainScreenEvent.ToCurrencySelect)
        })
    } else {
        MainContentLandscape(state = state, onEvent = onEvent, keys = keys, onSelectFromCurrency = {

            isSheetOpen = true
            onEvent(MainScreenEvent.FromCurrencySelect)
        }, onSelectToCurrency = {
            isSheetOpen = true
            onEvent(MainScreenEvent.ToCurrencySelect)
        })
    }
}

private fun a() {

}