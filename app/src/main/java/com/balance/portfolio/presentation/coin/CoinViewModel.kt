package com.balance.portfolio.presentation.coin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balance.portfolio.common.Resource
import com.balance.portfolio.data.mapping.toCoin
import com.balance.portfolio.data.mapping.toCoinEntity
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {
    val TAG = "pew CoinViewModel"

    var coinState by mutableStateOf(CoinState())
    var coinListState by mutableStateOf(CoinListState())

    var mockState by mutableStateOf(CoinListState())
    val mock_coins = listOf<Coin>(Coin("btc", 5.0, 1.0), Coin("eth", 0.2, 1.0))

    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = _coin

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getAllLocalCoins()
    }

    // todo : fix loading , implement amount * price
    private fun getAllLocalCoins() {
        viewModelScope.launch(Dispatchers.Main) {
            coinListState = coinListState.copy(isLoading = true)
            Log.i(TAG, "getAllLocalCoins: called")
            mockState = mockState.copy(
                coins = mock_coins,
                isLoading = false,
                error = null
            )
            val response = repository.getAllLocalCoins()

            when (response) {
                is Resource.Success -> {
                    val coins = response.data?.map { it.toCoin() }
                    coinListState = coinListState.copy(
                        coins = coins,
                        isLoading = false,
                        error = null
                    )
                    Log.i(TAG, "getAllLocalCoins: Success, ${response.data}")
                }
                is Resource.Error -> {
                    coinState = coinState.copy(
                        isLoading = false,
                        error = response.message,
                        coin = null
                    )
                    Log.i(TAG, "getAllLocalCoins: Error, ${response.message}")
                }
                is Resource.Loading -> {
                    // nothing
                    Log.i(TAG, "getAllLocalCoins: Loading")
                }
                else -> {}

            }
        }
    }

    private fun getLocalCoin(coinId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            coinState = coinState.copy(isLoading = true)

            val response = repository.getLocalCoin(coinId)

            when (response) {
                is Resource.Success -> {
                    coinState = coinState.copy(
                        coin = response.data?.toCoin(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    coinState = coinState.copy(
                        isLoading = false,
                        error = response.message,
                        coin = null
                    )
                }
                else -> {}
//                    is Resource.Loading -> {
//                        // nothing
//                    }
            }
        }
    }

    fun insert(coin: Coin) {
        viewModelScope.launch(Dispatchers.Main) {
            coinState = coinState.copy(isLoading = true)
            Log.i(TAG, "insert: method called ")

            val response = repository.insertLocalCoin(coin.toCoinEntity())

//            insertCoinUseCase.invoke(coin).also { result ->
//                Log.i(TAG, "insert: coinUsecase is called ")
//                when (result) {
//                    is Resource.Success -> {
//                        coinState = coinState.copy(
//                            coin = result.data?.toCoin(),
//                            isLoading = false,
//                            error = null
//                        )
//                        Log.i(TAG, "insert: coin Success ${result.data}")
//                    }
//                    is Resource.Error -> {
//                        coinState = coinState.copy(
//                            isLoading = false,
//                            error = result.message,
//                            coin = null
//                        )
//                        Log.i(TAG, "insert: coin Error ${result.message} ")
//                    }
////                    is Resource.Loading -> {
////                        // nothing
////                        Log.i(TAG, "insert: coin Loading ")
////                    }
//                    else -> Log.i(TAG, "insert: coin Something else happend $result ")
//                }
//            }
        }
    }

    fun update(coin: Coin) {
    }

    fun refreshScreen(){}

    fun deleteOne(coin: Coin) {
    }

}