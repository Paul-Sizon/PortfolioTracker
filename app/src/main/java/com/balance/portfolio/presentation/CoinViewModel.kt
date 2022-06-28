package com.balance.portfolio.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balance.portfolio.domain.model.Coin

class CoinViewModel : ViewModel() {
    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin> = _coin

}