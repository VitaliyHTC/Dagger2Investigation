package com.vitaliyhtc.dagger2investigation.di.module;

import com.vitaliyhtc.dagger2investigation.data.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.data.model.mapper.ProductMapper;
import com.vitaliyhtc.dagger2investigation.data.rest.ApiInterface;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = RetrofitModule.class)
public class ProductRepositoryModule {

    @Provides
    @ApplicationScope
    public ProductRepositoryImpl productRepository(ApiInterface apiInterface, ProductMapper productMapper) {
        return new ProductRepositoryImpl(apiInterface, productMapper);
    }

    @Provides
    public ProductMapper productMapper() {
        return new ProductMapper();
    }


}
