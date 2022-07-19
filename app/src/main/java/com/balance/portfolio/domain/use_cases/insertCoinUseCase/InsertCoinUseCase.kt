package com.balance.portfolio.domain.use_cases.insertCoinUseCase

import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.db.entities.CoinEntity
import com.balance.portfolio.data.mapping.toCoinEntity
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.repository.CoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsertCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coin: Coin): Resource<CoinEntity> {
        try {
//            return Resource.Loading<Coin>()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertLocalCoin(coin.toCoinEntity())
            }
            return Resource.Success<CoinEntity>(coin.toCoinEntity())
//            emit(Resource.Success<Coin>(coin))
        } catch (e: NullPointerException) {
            e.printStackTrace()
            return Resource.Error<CoinEntity>(e.localizedMessage ?: "No info on coin")
        } catch (e: HttpException) {
            e.printStackTrace()
            return Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            e.printStackTrace()
            return Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }
}