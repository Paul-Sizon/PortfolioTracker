package com.balance.portfolio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.balance.portfolio.data.db.entities.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}