package com.vitaliyhtc.dagger2investigation.presentation.productslist.view;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.base.mvp.BaseView;

import java.util.List;

public interface ProductsListView extends BaseView {
    void addProductsToResult(List<Product> products);
    void loadProductsError(Throwable t);
    void launchProductDetailsActivity(int productId);
}
