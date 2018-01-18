package com.vitaliyhtc.dagger2investigation.view;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.List;

public interface ProductsListView extends BaseView {
    void addProductsToResult(List<Product> products);
    void loadProductsError(Throwable t);
    void launchProductDetailsActivity(int productId);
}
