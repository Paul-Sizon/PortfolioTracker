package com.balance.portfolio.data.remote

import com.balance.portfolio.domain.model.Coin

data class CoinDto(
    val asset_id_base: String,
    val asset_id_quote: String,
    val rate: Double,
    val time: String
)

fun CoinDto.toCoin(): Coin{
    return Coin(
        price = rate,
        name = "todo_name"
    )
}