package com.balance.portfolio.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.presentation.coin.CoinViewModel
import com.balance.portfolio.theme.PortfolioTheme

val mock_coins = listOf<Coin>(Coin("btc", 5.0, 1.0), Coin("eth", 0.2, 1.0))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: CoinViewModel = hiltViewModel<CoinViewModel>()
) {
    val state = viewModel.state
    state.coin?.let { Element(coin = it) }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(80.dp),
                onClick = {
                    navController.navigate(Screen.NewCoinScreen.route)
                }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "add new item",
                    modifier = Modifier.size(45.dp),
                    tint = Color.Gray
                )
            }
        },
        modifier = Modifier.padding(),
        topBar = {},
        bottomBar = {},
        content = { contentPadding ->
            LazyColumn(modifier = Modifier.padding(contentPadding)) {
                items(mock_coins) { coin ->
                    Element(coin = coin)
                    Spacer(modifier = Modifier.padding(bottom = 4.dp))
                }
            }
        })
}


@Composable
fun Element(coin: Coin) {
    Row() {
        Text(text = coin.name)
        Spacer(modifier = Modifier.padding(horizontal = 30.dp))
        Text(text = coin.price.toString())
        Spacer(modifier = Modifier.padding(horizontal = 30.dp))
        Text(text = coin.amount.toString())
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PortfolioTheme {

    }
}