package com.vitaliyhtc.dagger2investigation.presentation.productdetails.di

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository
import com.vitaliyhtc.dagger2investigation.presentation.productdetails.presenter.ProductDetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class ProductDetailsModule {

    @Provides
    @ProductDetailsActivityScope
    fun provideProductDetailsPresenter(productRepository: ProductRepository): ProductDetailsPresenter {
        return ProductDetailsPresenter(productRepository)
    }
}