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
        EnterCoinName()
        EnterAmount()
        AddButton(navController, viewModel)
    }
}

@Composable
fun EnterCoinName() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text("Coin name") }
    )
}

@Composable
fun EnterAmount() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text("Amount") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


@Composable
fun AddButton(
    navController: NavController,
    viewModel: CoinViewModel
) {
    Button(
        onClick = {
//            viewModel.insert(coin)
            navController.navigate(Screen.MainScreen.route)
        })
    {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
    }
}