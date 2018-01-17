package com.vitaliyhtc.dagger2investigation.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface.LCBO_API_BASE_URL;

@Module(includes = OkHttpClientModule.class)
public class RetrofitModule {

    private static final String BASE_URL = LCBO_API_BASE_URL;


    @Provides
    public ApiInterface apiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @ApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory, Gson gson,
                             RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

}
