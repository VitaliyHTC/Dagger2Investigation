package com.vitaliyhtc.dagger2investigation.di.module;

import com.vitaliyhtc.dagger2investigation.ProductsListActivity;

import dagger.Module;

@Module
public class MainActivityModule {

    private final ProductsListActivity mProductsListActivity;

    public MainActivityModule(ProductsListActivity productsListActivity) {
        mProductsListActivity = productsListActivity;
    }
}
