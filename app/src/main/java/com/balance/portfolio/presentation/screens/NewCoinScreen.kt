package com.balance.portfolio.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.presentation.coin.CoinViewModel

//todo: add Coin, save it to db, better ui
@Composable
fun NewCoinScreen(
    navController: NavController,
    viewModel: CoinViewModel = hiltViewModel<CoinViewModel>()
) {
    val data =""
    Column(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        var name by rememberSaveable { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text("Coin name") }
        )


        var amount by rememberSaveable { mutableStateOf("") }
        TextField(
            value = amount,
            onValueChange = {
                amount = it
            },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        //todo change it to API price
        var price by rememberSaveable { mutableStateOf("") }
        TextField(
            value = price,
            onValueChange = {
                price = it
            },
            label = { Text("price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        Button(
            onClick = {
            viewModel.insert(
                Coin(
                    name = name,
                    amount = amount.toDouble(),
                    price = 0.0,
                )
            )
                navController.navigate(Screen.MainScreen.route)
            })
        {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add")
        }

    }
}