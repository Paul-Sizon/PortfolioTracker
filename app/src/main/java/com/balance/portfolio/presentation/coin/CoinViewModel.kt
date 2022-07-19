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
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.use_cases.getAllLocalCoins.GetAllCoinsUseCase
import com.balance.portfolio.domain.use_cases.getOneLocalCoin.GetOneLocalCoinUseCase
import com.balance.portfolio.domain.use_cases.insertCoinUseCase.InsertCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase,
    private val getOneLocalCoinUseCase: GetOneLocalCoinUseCase,
    private val insertCoinUseCase: InsertCoinUseCase
) : ViewModel() {
    val TAG = "pew CoinViewModel"

    var coinState by mutableStateOf(CoinState())
    var coinsState by mutableStateOf(CoinsState())

    var mockState by mutableStateOf(CoinsState())
    val mock_coins = listOf<Coin>(Coin("btc", 5.0, 1.0), Coin("eth", 0.2, 1.0))

    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = _coin

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getAllLocalCoins()
    }

    private fun getAllLocalCoins() {
        viewModelScope.launch(Dispatchers.Main) {
            coinsState = coinsState.copy(isLoading = true)
            Log.i(TAG, "getAllLocalCoins: called")
            mockState = mockState.copy(
                coins = mock_coins,
                isLoading = false,
                error = null
            )
            getAllCoinsUseCase().onStart { Resource.Loading }.collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        coinsState = coinsState.copy(
                            coins = result.data,
                            isLoading = false,
                            error = null
                        )
                        Log.i(TAG, "getAllLocalCoins: Success, ${result.data}")
                    }
                    is Resource.Error -> {
                        coinState = coinState.copy(
                            isLoading = false,
                            error = result.message,
                            coin = null
                        )
                        Log.i(TAG, "getAllLocalCoins: Error, ${result.message}")
                    }
                    else -> {}
//                     is Resource.Loading -> {
//                        // nothing
//                        Log.i(TAG, "getAllLocalCoins: Loading")
//                    }
                }
            }
        }
    }

    private fun getLocalCoin(coinId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            coinState = coinState.copy(isLoading = true)

            getOneLocalCoinUseCase.invoke(coinId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        coinState = coinState.copy(
                            coin = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        coinState = coinState.copy(
                            isLoading = false,
                            error = result.message,
                            coin = null
                        )
                    }
                    else ->{}
//                    is Resource.Loading -> {
//                        // nothing
//                    }
                }
            }
        }
    }

    fun insert(coin: Coin) {
        viewModelScope.launch(Dispatchers.Main) {
            coinState = coinState.copy(isLoading = true)
            Log.i(TAG, "insert: method called ")

            insertCoinUseCase.invoke(coin).also { result ->
                Log.i(TAG, "insert: coinUsecase is called ")
                when (result) {
                    is Resource.Success -> {
                        coinState = coinState.copy(
                            coin = result.data?.toCoin(),
                            isLoading = false,
                            error = null
                        )
                        Log.i(TAG, "insert: coin Success ${result.data}")
                    }
                    is Resource.Error -> {
                        coinState = coinState.copy(
                            isLoading = false,
                            error = result.message,
                            coin = null
                        )
                        Log.i(TAG, "insert: coin Error ${result.message} ")
                    }
//                    is Resource.Loading -> {
//                        // nothing
//                        Log.i(TAG, "insert: coin Loading ")
//                    }
                    else -> Log.i(TAG, "insert: coin Something else happend $result ")
                }
            }
        }
    }

    fun update(coin: Coin) {
    }

    fun deleteOne(coin: Coin) {
    }

}