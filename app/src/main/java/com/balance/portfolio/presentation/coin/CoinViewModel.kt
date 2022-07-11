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
import com.balance.portfolio.domain.use_cases.getOneLocalCoin.GetOneLocalCoinUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

//todo: add state like Phillip has

class CoinViewModel @Inject constructor(
    private val getOneLocalCoinUseCase: GetOneLocalCoinUseCase
) : ViewModel() {

    var state by mutableStateOf(CoinState())



    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = _coin

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun getLocalCoin() {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)

            getOneLocalCoinUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
//                        _coin.value = result.data!!
                        state = state.copy(
                            coin = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
//                        _error.value = result.message!!
                        state = state.copy(
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
    }

    fun update(coin: Coin) {
    }

    fun deleteOne(coin: Coin) {
    }

}