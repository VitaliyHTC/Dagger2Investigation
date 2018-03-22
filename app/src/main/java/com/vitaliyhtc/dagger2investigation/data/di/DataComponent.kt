package com.vitaliyhtc.dagger2investigation.data.di

import android.content.Context
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.global.App
import com.vitaliyhtc.dagger2investigation.global.di.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,

            DataModule::class,
            RetrofitModule::class
        ]
)
interface DataComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): DataComponent
    }

    fun getContext(): Context

    fun getProductRepository(): ProductRepository
}