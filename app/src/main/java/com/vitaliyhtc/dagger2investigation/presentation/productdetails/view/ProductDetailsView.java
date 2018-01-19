package com.vitaliyhtc.dagger2investigation.presentation.productdetails.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ProductDetailsView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showProduct(Product product);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void loadProductsError(Throwable t);
}
