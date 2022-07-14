package com.balance.portfolio.presentation.screens

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object NewCoinScreen : Screen("NewCoinScreen")
}