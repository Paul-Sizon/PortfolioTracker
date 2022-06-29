package com.balance.portfolio.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CoinApi {

    @GET("v1/exchangerate/{coinId}/USD/")
    @Headers("X-CoinAPI-Key: $apiKey")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDto

    companion object {
        const val BASE_URL = "https://rest.coinapi.io/"

        //todo use another in production
        const val apiKey = "4F635389-87E1-431E-B0F7-0678EA8DDCCB"
    }

}