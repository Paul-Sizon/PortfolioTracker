package com.balance.portfolio.domain.use_cases.getAllLocalCoins

//todo: implement all coins use-case

//class GetAllCoinsUseCase @Inject constructor(private val repository: CoinRepository, private val coinName: String){
//    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
//        try {
//            emit(Resource.Loading<List<Coin>>())
//            val coins = repository.getLocalCoin(coinName)
////            emit(Resource.Success(coins.map { it.data.toCoin() }))
//            emit(Resource.Success(coins.data.)
//        } catch(e: HttpException) {
//            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
//        } catch(e: IOException) {
//            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
//        }
//    }
//}