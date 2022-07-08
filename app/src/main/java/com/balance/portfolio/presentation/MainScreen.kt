package com.balance.portfolio.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.theme.PortfolioTheme

val mock_coins = listOf<Coin>(Coin("btc", 5.0, 1.0), Coin("eth", 0.2, 1.0))

@Composable
fun MainScreen(
    viewModel: CoinViewModel = hiltViewModel()
) {
    val coin = viewModel.coin.asFlow()

    LazyColumn() {
        items(mock_coins) { coin ->
            Element(coin = coin)
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
        }
    }
}

@Composable
fun Element(coin: Coin) {
    Row() {
        Text(text = coin.name)
        Spacer(modifier = Modifier.padding(horizontal = 30.dp))
        Text(text = coin.price.toString())
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PortfolioTheme {
        MainScreen()
    }
}