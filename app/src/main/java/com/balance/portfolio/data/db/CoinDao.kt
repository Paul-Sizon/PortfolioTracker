package com.balance.portfolio.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.balance.portfolio.data.db.entities.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert
    suspend fun insert(coin: CoinEntity)

    @Update
    suspend fun update(coin: CoinEntity)

    @Delete
    suspend fun deleteOne(coin: CoinEntity)

    @Query("DELETE FROM coin_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM coin_table WHERE uid =:coinId")
    suspend fun getOneCoin(coinId: Int): CoinEntity?

    @Query("SELECT * FROM coin_table ORDER BY uid DESC")
    fun getAllCoinsDesc(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM coin_table ORDER BY uid ASC")
    fun getAllCoinsAsc(): List<CoinEntity>

    @Query("SELECT COUNT(*) FROM coin_table")
    suspend fun getCount(): Int

}