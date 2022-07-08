package com.balance.portfolio.domain.use_cases.getOneLocalCoin

import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.mapping.toCoin
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOneLocalCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val coinId: Int
) {
    operator fun invoke(): Flow<Resource<Coin>> = flow {
        try {
            emit(Resource.Loading<Coin>())
            val coins = repository.getLocalCoin(coinId)

            emit(Resource.Success(coins.data!!.toCoin()))
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