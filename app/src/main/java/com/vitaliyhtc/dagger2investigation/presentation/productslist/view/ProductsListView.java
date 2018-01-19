package com.vitaliyhtc.dagger2investigation.presentation.productslist.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ProductsListView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void addProductsToResult(List<Product> products);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void loadProductsError(Throwable t);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void launchProductDetailsActivity(int productId);
}
