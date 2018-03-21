package com.vitaliyhtc.dagger2investigation.data.di

import com.vitaliyhtc.dagger2investigation.data.repository.ProductRepositoryImpl
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiInterface: ApiInterface): ProductRepository {
        return ProductRepositoryImpl(apiInterface)
    }
}