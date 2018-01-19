package com.vitaliyhtc.dagger2investigation.global;

import android.app.Activity;
import android.app.Application;

import com.vitaliyhtc.dagger2investigation.BuildConfig;
import com.vitaliyhtc.dagger2investigation.data.di.DaggerDataComponent;
import com.vitaliyhtc.dagger2investigation.data.di.DataComponent;
import com.vitaliyhtc.dagger2investigation.global.di.AppComponent;
import com.vitaliyhtc.dagger2investigation.global.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class Dagger2InvestigationApp extends Application implements HasActivityInjector {

    private static Dagger2InvestigationApp sInstance;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    private AppComponent mAppComponent;
    private DataComponent mDataComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        initTimber();

        initDagger();
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    private void initDagger() {
        mDataComponent = DaggerDataComponent.builder()
                .application(this)
                .build();

        mAppComponent = DaggerAppComponent.builder()
                .dataComponent(mDataComponent)
                .build();

        mAppComponent.inject(this);
    }

    public static Dagger2InvestigationApp getInstance() {
        return sInstance;
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }


    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
