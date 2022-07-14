package com.balance.portfolio.domain.use_cases.insertCoinUseCase

import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.mapping.toCoin
import com.balance.portfolio.data.mapping.toCoinEntity
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsertCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coin: Coin): Flow<Resource<Coin>> = flow {
        try {
            emit(Resource.Loading<Coin>())
            repository.insertLocalCoin(coin.toCoinEntity())
        } catch (e: NullPointerException) {
            e.printStackTrace()
            emit(Resource.Error<Coin>(e.localizedMessage ?: "No info on coin"))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error<Coin>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error<Coin>("Couldn't reach server. Check your internet connection."))
        }
    }
}