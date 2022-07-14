package com.balance.portfolio.presentation.coin

import com.balance.portfolio.domain.model.Coin

data class CoinState(
    val coin: Coin? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CoinsState(
    val coin: List<Coin>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)