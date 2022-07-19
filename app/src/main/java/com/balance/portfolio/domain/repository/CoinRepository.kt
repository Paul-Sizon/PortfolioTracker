package com.balance.portfolio.domain.repository

import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.db.entities.CoinEntity
import com.balance.portfolio.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getLocalCoin(
        coinId: Int
    ): Resource<CoinEntity>

     suspend fun insertLocalCoin(coin: CoinEntity): Resource<CoinEntity>

    suspend fun getAllLocalCoins(): Flow<List<CoinEntity>>
//
//
//    suspend fun getRemoteCoin(
//        query: String
//    ): Flow<Resource<CoinEntity>>
//
//    suspend fun getRemoteCoins(
//        query: String
//    ): Flow<Resource<List<CoinEntity>>>

}