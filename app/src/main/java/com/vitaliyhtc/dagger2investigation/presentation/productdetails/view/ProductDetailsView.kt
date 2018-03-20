package com.vitaliyhtc.dagger2investigation.presentation.productdetails.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vitaliyhtc.dagger2investigation.domain.model.Product

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProductDetailsView : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showProduct(product: Product)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun loadProductsError(t: Throwable)
}