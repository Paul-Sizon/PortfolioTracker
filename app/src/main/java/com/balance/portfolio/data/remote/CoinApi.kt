package com.balance.portfolio.data.remote

import com.balance.portfolio.data.api_key
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CoinApi {

    @GET("v1/exchangerate/{coinId}/USD/")
    @Headers("X-CoinAPI-Key: $api_key")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDto

    companion object {
        const val BASE_URL = "https://rest.coinapi.io/"
    }

}