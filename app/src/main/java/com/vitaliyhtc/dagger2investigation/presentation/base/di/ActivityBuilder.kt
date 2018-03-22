package com.vitaliyhtc.dagger2investigation.presentation.base.di

import com.vitaliyhtc.dagger2investigation.presentation.productdetails.di.ProductDetailsActivityScope
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.di.ProductDetailsModule
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.view.ProductDetailsActivity
import com.vitaliyhtc.dagger2investigation.presentation.productslist.di.ProductsListActivityScope
import com.vitaliyhtc.dagger2investigation.presentation.productslist.di.ProductsListModule
import com.vitaliyhtc.dagger2investigation.presentation.productslist.view.ProductsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    // TODO: 3/22/18 why you need brackets here?
    @ContributesAndroidInjector(modules = [(ProductsListModule::class)])
    @ProductsListActivityScope
    internal abstract fun bindProductsListActivity(): ProductsListActivity

    @ContributesAndroidInjector(modules = [(ProductDetailsModule::class)])
    @ProductDetailsActivityScope
    internal abstract fun bindProductDetailsActivity(): ProductDetailsActivity
}