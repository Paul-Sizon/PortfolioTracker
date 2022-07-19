package com.balance.portfolio.data.repository

import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.db.CoinDatabase
import com.balance.portfolio.data.db.entities.CoinEntity
import com.balance.portfolio.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImpl @Inject constructor(
//    private val api: CoinApi,
    db: CoinDatabase,
) : CoinRepository {

    private val dao = db.coinDao()

    override suspend fun getLocalCoin(coinId: Int): Resource<CoinEntity> {
        return try {
            val response = dao.getOneCoin(coinId)
            Resource.Success<CoinEntity>(response!!)
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Resource.Error<CoinEntity>(e.localizedMessage ?: "No info on coin")
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error<CoinEntity>("io exception")
        }
    }

    override suspend fun insertLocalCoin(coin: CoinEntity): Resource<CoinEntity> {
        return try {
            dao.insert(coin)
            Resource.Success<CoinEntity>(coin)
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Resource.Error<CoinEntity>(e.localizedMessage ?: "null pointer exception")
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error<CoinEntity>("io exception")
        }
    }

    override suspend fun getAllLocalCoins() = dao.getAllCoinsDesc()
    //todo: api call to get the price
//
//    override suspend fun getRemoteCoin(query: String): Flow<Resource<CoinEntity>> {
//
//    }
//
//    override suspend fun getRemoteCoins(query: String): Flow<Resource<List<CoinEntity>>> {
//
//    }
}