package com.balance.portfolio.presentation.coin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balance.portfolio.common.Resource
import com.balance.portfolio.domain.model.Coin
import com.balance.portfolio.domain.use_cases.getAllLocalCoins.GetAllCoinsUseCase
import com.balance.portfolio.domain.use_cases.getOneLocalCoin.GetOneLocalCoinUseCase
import com.balance.portfolio.domain.use_cases.insertCoinUseCase.InsertCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase,
    private val getOneLocalCoinUseCase: GetOneLocalCoinUseCase,
    private val insertCoinUseCase: InsertCoinUseCase
) : ViewModel() {

    var coinState by mutableStateOf(CoinState())
    var coinsState by mutableStateOf(CoinsState())

    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = _coin

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getAllLocalCoins()
    }

    private fun getAllLocalCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            coinsState = coinsState.copy(isLoading = true)

            getAllCoinsUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        coinsState = coinsState.copy(
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
                    is Resource.Loading -> {
                        // nothing
                    }
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
                    is Resource.Loading -> {
                        // nothing
                    }
                }
            }
        }
    }

    fun insert(coin: Coin) {
        viewModelScope.launch(Dispatchers.IO) {
            coinState = coinState.copy(isLoading = true)

            insertCoinUseCase.invoke(coin).onEach { result ->
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
                    is Resource.Loading -> {
                        // nothing
                    }
                }
            }
        }
    }

    fun update(coin: Coin) {
    }

    fun deleteOne(coin: Coin) {
    }

}