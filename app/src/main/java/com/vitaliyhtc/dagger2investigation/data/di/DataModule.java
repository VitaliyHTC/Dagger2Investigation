package com.vitaliyhtc.dagger2investigation.data.di;

import com.vitaliyhtc.dagger2investigation.data.repository.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ProductRepository provideProductRepository(ApiInterface apiInterface) {
        return new ProductRepositoryImpl(apiInterface);
    }
}
