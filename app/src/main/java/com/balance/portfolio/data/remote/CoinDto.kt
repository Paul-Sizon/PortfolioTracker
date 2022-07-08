package com.balance.portfolio.data.remote

data class CoinDto(
    val asset_id_base: String,
    val asset_id_quote: String,
    val rate: Double,
    val time: String
)