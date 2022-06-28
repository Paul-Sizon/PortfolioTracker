package com.balance.portfolio.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Portfolio()
                }
            }
        }
    }
}

val mock_coins = listOf<Coin>(Coin("btc", 5.0), Coin("eth", 0.2))

@Composable
fun Portfolio() {
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
        Portfolio()
    }
}