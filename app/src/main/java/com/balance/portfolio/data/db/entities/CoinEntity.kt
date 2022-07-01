package com.balance.portfolio.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.balance.portfolio.domain.model.Coin

@Entity(tableName = "coin_table")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "amount") val amount: Double?
)

fun CoinEntity.toCoin(): Coin{
    return Coin(
        name = name?: "",
        amount = amount?: 555.0,
        price = 1111.0
    )
}