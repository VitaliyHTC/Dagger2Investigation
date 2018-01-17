package com.vitaliyhtc.dagger2investigation.view;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;

public interface MainView extends BaseView {
    void addProductToResult(Product product);
    void loadProductsError(Throwable t);
}
