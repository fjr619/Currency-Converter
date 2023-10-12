package com.fjr619.currencyconverter.di

import android.app.Application
import androidx.room.Room
import com.fjr619.currencyconverter.data.local.CurrencyRateDatabase
import com.fjr619.currencyconverter.data.remote.CurrencyApi
import com.fjr619.currencyconverter.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyApi {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CurrencyRateDatabase {
        return Room
            .databaseBuilder(
                application,
                CurrencyRateDatabase::class.java,
                "currency_db"
            )
            .build()
    }

}