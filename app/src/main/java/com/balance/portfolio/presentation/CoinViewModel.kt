package com.balance.portfolio.presentation

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

    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = _coin

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun getLocalCoin() {
        viewModelScope.launch(Dispatchers.IO) {
            getOneLocalCoinUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _coin.value = result.data!!
                    }
                    is Resource.Error -> {
                        _error.value = result.message!!
                    }
                    is Resource.Loading -> {
                        //todo: loading
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