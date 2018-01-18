package com.vitaliyhtc.dagger2investigation.di.module;

import com.google.gson.GsonBuilder;
import com.vitaliyhtc.dagger2investigation.BuildConfig;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RetrofitModule {
    private static final String BASE_URL = BuildConfig.LCBO_API_BASE_URL;

    @ApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).create()))
                .build();
    }

    @Provides
    public ApiInterface apiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

}
