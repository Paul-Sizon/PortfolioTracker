package com.balance.portfolio.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balance.portfolio.presentation.screens.MainScreen
import com.balance.portfolio.presentation.screens.NewCoinScreen
import com.balance.portfolio.presentation.screens.Screen
import com.balance.portfolio.theme.PortfolioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                PortfolioApp()
            }
        }
    }
}

@Composable
fun PortfolioApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable(Screen.MainScreen.route) { MainScreen(navController) }
        composable(Screen.NewCoinScreen.route) { NewCoinScreen(navController) }
    }
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//
//    }
}
