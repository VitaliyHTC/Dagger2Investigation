package com.vitaliyhtc.dagger2investigation.data.di

import android.arch.persistence.room.Room
import com.vitaliyhtc.dagger2investigation.data.db.AppDatabase
import com.vitaliyhtc.dagger2investigation.data.repository.ProductRepositoryImpl
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.global.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiInterface: ApiInterface, appDatabase: AppDatabase): ProductRepository {
        return ProductRepositoryImpl(apiInterface, appDatabase)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase =
            Room.databaseBuilder(App.getInstance(), AppDatabase::class.java, "databaseName").build()

}