package com.vitaliyhtc.dagger2investigation.di.component;

import com.vitaliyhtc.dagger2investigation.data.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.di.module.MainActivityModule;
import com.vitaliyhtc.dagger2investigation.interfaces.MainActivityScope;
import com.vitaliyhtc.dagger2investigation.view.adapter.ProductsListAdapter;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = ProductDataComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    ProductsListAdapter getProductsListAdapter();

    ProductRepositoryImpl getProductRepository();
}
