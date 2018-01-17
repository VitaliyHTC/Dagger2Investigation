package com.vitaliyhtc.dagger2investigation.di.module;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationContext;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @ApplicationScope
    @Provides
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader).
                build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

}