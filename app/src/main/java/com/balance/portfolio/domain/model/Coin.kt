package com.balance.portfolio.domain.model

data class Coin(
    val name: String,
    val price: Double,
    val amount: Double,
    val total: Double = price * amount
)