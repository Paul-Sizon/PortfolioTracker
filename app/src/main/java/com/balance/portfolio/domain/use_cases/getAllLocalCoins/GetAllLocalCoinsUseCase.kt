package com.balance.portfolio.domain.use_cases.getAllLocalCoins


import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.mapping.toCoin
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            repository.getAllLocalCoins().onEach { coin ->
                coin.data?.map { it.toCoin() }
                    .also { emit(Resource.Success(it!!)) }
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "empty coin"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}