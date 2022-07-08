package com.balance.portfolio.data.mapping

import com.balance.portfolio.data.db.entities.CoinEntity
import com.balance.portfolio.data.remote.CoinDto
import com.balance.portfolio.domain.model.Coin

fun CoinEntity.toCoin(): Coin {
    return Coin(
        name = name ?: "",
        amount = amount ?: 555.0,
        price = 1111.0
    )
}

fun CoinDto.toCoin(): Coin {
    return Coin(
        price = rate,
        name = "todo_name",
        amount = 42.0
    )
}