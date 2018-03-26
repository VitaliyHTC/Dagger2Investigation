package com.vitaliyhtc.dagger2investigation.global

import android.app.Activity
import android.app.Application
import com.vitaliyhtc.dagger2investigation.BuildConfig
import com.vitaliyhtc.dagger2investigation.data.di.DaggerDataComponent
import com.vitaliyhtc.dagger2investigation.data.di.DataComponent
import com.vitaliyhtc.dagger2investigation.global.di.AppComponent
import com.vitaliyhtc.dagger2investigation.global.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var mAppComponent: AppComponent
    private lateinit var mDataComponent: DataComponent

    companion object {
        private lateinit var sInstance: App

        fun getInstance() = sInstance
    }

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