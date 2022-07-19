package com.balance.portfolio.domain.use_cases.getAllLocalCoins


import android.util.Log
import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.mapping.toCoin
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    val TAG = "usecase GetAllCoinsUseCase"
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            Log.i(TAG, "invoke: success called")
            repository.getAllLocalCoins().collect(){ coin ->
                emit(Resource.Success(coin.map { it.toCoin() }))
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            emit(Resource.Error(e.message?: "empty coin"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}