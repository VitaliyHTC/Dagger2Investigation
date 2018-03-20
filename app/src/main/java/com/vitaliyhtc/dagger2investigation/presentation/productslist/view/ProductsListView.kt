package com.vitaliyhtc.dagger2investigation.presentation.productslist.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vitaliyhtc.dagger2investigation.domain.model.Product

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProductsListView : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun addProductsToResult(products: List<Product>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun loadProductsError(t: Throwable)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun launchProductDetailsActivity(productId: Int)
}