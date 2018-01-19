package com.vitaliyhtc.dagger2investigation.presentation.base.di;

import com.vitaliyhtc.dagger2investigation.presentation.productdetails.ProductDetailsActivity;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.di.ProductDetailsActivityScope;
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.di.ProductDetailsModule;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.ProductsListActivity;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.di.ProductsListActivityScope;
import com.vitaliyhtc.dagger2investigation.presentation.productslist.di.ProductsListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ProductsListModule.class)
    @ProductsListActivityScope
    abstract ProductsListActivity bindProductsListActivity();

    @ContributesAndroidInjector(modules = ProductDetailsModule.class)
    @ProductDetailsActivityScope
    abstract ProductDetailsActivity bindProductDetailsActivity();
}
