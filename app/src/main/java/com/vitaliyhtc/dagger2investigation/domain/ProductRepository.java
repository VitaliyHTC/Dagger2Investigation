package com.vitaliyhtc.dagger2investigation.domain;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.List;

import io.reactivex.Single;

public interface ProductRepository {

    Single<List<Product>> getProducts(int page);

    Single<Product> getOneProduct(int productId);
}
