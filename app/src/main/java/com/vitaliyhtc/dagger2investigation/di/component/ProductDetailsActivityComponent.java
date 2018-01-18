package com.vitaliyhtc.dagger2investigation.di.component;

import com.vitaliyhtc.dagger2investigation.data.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.interfaces.ProductDetailsActivityScope;

import dagger.Component;

@Component(dependencies = ProductDataComponent.class)
@ProductDetailsActivityScope
public interface ProductDetailsActivityComponent {

    ProductRepositoryImpl getProductRepository();
}
