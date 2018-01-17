package com.vitaliyhtc.dagger2investigation.domain;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.List;

import io.reactivex.Observable;

public interface ProductRepository {

    Observable<List<Product>> getProductsObservable(int page);

    Observable<Product> getOneProductsObservable(int productId);
}
