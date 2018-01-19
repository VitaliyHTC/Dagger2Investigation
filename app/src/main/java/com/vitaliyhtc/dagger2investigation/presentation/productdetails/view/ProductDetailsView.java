package com.vitaliyhtc.dagger2investigation.presentation.productdetails.view;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;
import com.vitaliyhtc.dagger2investigation.presentation.base.mvp.BaseView;

public interface ProductDetailsView extends BaseView {

    void showProduct(Product product);
    void loadProductsError(Throwable t);
}
