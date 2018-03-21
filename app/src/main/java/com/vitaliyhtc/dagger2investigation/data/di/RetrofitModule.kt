package com.vitaliyhtc.dagger2investigation.data.di

import com.google.gson.GsonBuilder
import com.vitaliyhtc.dagger2investigation.BuildConfig
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class RetrofitModule {
    private val baseUrl = BuildConfig.LCBO_API_BASE_URL

    @Provides
    @Singleton
    fun apiInterface(): ApiInterface = getRetrofit().create(ApiInterface::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(getHttpLoggingInterceptor())
                .build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}