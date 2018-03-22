package com.vitaliyhtc.dagger2investigation.presentation.productslist.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*
import com.vitaliyhtc.dagger2investigation.domain.model.Product

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProductsListView : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun addProductsToResult(products: List<Product>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun loadProductsError(t: Throwable)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun launchProductDetailsActivity(productId: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showLoadingInProgress()

    @StateStrategyType(SkipStrategy::class)
    fun hideLoadingInProgress()
}