package com.balance.portfolio.di

import android.app.Application
import androidx.room.Room
import com.balance.portfolio.data.db.CoinDatabase
import com.balance.portfolio.data.repository.CoinRepositoryImpl
import com.balance.portfolio.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideCoinApi(): CoinApi {
//        return Retrofit.Builder()
//            .baseUrl(CoinApi.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create()
//    }

    @Provides
    @Singleton
    fun provideCoinDatabase(app: Application): CoinDatabase {
        return Room.databaseBuilder(
            app,
            CoinDatabase::class.java,
            "coinDB.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        db: CoinDatabase
    ): CoinRepository {
        return CoinRepositoryImpl(db)
    }
}