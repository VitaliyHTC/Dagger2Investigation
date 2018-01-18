package com.vitaliyhtc.dagger2investigation.data.di;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.vitaliyhtc.dagger2investigation.BuildConfig;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class RetrofitModule {
    private static final String BASE_URL = BuildConfig.LCBO_API_BASE_URL;
    
    @Provides
    @Singleton
    public ApiInterface apiInterface() {
        return getRetrofit().create(ApiInterface.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

}
