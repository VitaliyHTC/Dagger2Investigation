package com.vitaliyhtc.dagger2investigation.global.di

import android.content.Context
import com.vitaliyhtc.dagger2investigation.global.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application.applicationContext

}