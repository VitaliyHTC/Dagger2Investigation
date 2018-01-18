package com.vitaliyhtc.dagger2investigation.di.component;

import com.vitaliyhtc.dagger2investigation.data.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.di.module.ProductRepositoryModule;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = ProductRepositoryModule.class)
public interface ProductDataComponent {

    ProductRepositoryImpl getProductRepository();
}
