package com.vitaliyhtc.dagger2investigation.data.model.mapper;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class ProductsMapper implements Function<List<com.vitaliyhtc.dagger2investigation.data.model.response.Product>, List<Product>> {

    private ProductMapper mPm = new ProductMapper();

    @Override
    public List<Product> apply(List<com.vitaliyhtc.dagger2investigation.data.model.response.Product> products) throws Exception {
        List<Product> list = new ArrayList<>();
        for (com.vitaliyhtc.dagger2investigation.data.model.response.Product product1 : products) {
            list.add(mPm.apply(product1));
        }
        return list;
    }
}
