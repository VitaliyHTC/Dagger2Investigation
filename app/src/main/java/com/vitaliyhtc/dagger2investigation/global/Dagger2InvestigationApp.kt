package com.vitaliyhtc.dagger2investigation.global

import android.app.Activity
import android.app.Application
import com.vitaliyhtc.dagger2investigation.BuildConfig
import com.vitaliyhtc.dagger2investigation.data.di.DataComponent
import com.vitaliyhtc.dagger2investigation.global.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

//TODO: check where to place static fields and functions.
// can be inside as companion object
private lateinit var sInstance: Dagger2InvestigationApp

// TODO: 3/22/18 rename it to App
class Dagger2InvestigationApp : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var mAppComponent: AppComponent
    private lateinit var mDataComponent: DataComponent

    override fun onCreate() {
        super.onCreate()

        sInstance = this
        initDagger()
        initTimber()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    private fun initDagger() {
        mDataComponent = DaggerDataComponent.builder()
                .application(this)
                .build()

        mAppComponent = DaggerAppComponent.builder()
                .dataComponent(mDataComponent)
                .build()

        mAppComponent.inject(this)
    }

    fun getDataComponent(): DataComponent = mDataComponent

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

// TODO: 3/22/18 check this also
fun getInstance(): Dagger2InvestigationApp = sInstance