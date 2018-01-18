package com.vitaliyhtc.dagger2investigation.presentation.productslist.di;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.presenter.ProductsListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductsListModule {

    @Provides
    @ProductsListActivityScope
    ProductsListPresenter provideProductsListPresenter(ProductRepository productRepository) {
        return new ProductsListPresenter(productRepository);
    }
}
