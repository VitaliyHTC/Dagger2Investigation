package com.vitaliyhtc.dagger2investigation.global.di;

import android.content.Context;

import com.vitaliyhtc.dagger2investigation.global.Dagger2InvestigationApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Dagger2InvestigationApp application) {
        return application.getApplicationContext();
    }
}
