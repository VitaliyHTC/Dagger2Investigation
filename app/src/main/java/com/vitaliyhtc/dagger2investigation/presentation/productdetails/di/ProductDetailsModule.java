package com.vitaliyhtc.dagger2investigation.presentation.productdetails.di;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter.ProductDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailsModule {

    @Provides
    @ProductDetailsActivityScope
    ProductDetailsPresenter provideProductDetailsPresenter(ProductRepository productRepository) {
        return new ProductDetailsPresenter(productRepository);
    }
}
