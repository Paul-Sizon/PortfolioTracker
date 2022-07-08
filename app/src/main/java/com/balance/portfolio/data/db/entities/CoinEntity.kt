package com.balance.portfolio.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_table")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "amount") val amount: Double?
)
