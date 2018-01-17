package com.vitaliyhtc.dagger2investigation.di.module;

import android.content.Context;

import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationContext;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @ApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
