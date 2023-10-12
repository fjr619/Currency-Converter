package com.fjr619.currencyconverter.di

import com.fjr619.currencyconverter.data.repository.CurrencyRateRepositoryImpl
import com.fjr619.currencyconverter.domain.repository.ICurrencyRateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBind {

    @Binds
    abstract fun bindRepository(
        repositoryImpl: CurrencyRateRepositoryImpl
    ): ICurrencyRateRepository
}