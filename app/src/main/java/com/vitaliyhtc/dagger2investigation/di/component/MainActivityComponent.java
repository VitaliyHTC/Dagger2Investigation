package com.vitaliyhtc.dagger2investigation.di.component;

import com.vitaliyhtc.dagger2investigation.data.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.di.module.MainActivityModule;
import com.vitaliyhtc.dagger2investigation.interfaces.MainActivityScope;
import com.vitaliyhtc.dagger2investigation.view.adapter.ProductsListAdapter;

import dagger.Component;

// TODO: 1/17/18 split 'di' packages by they meeaning https://drive.google.com/file/d/1pQQZFY1DMmAoMSyb_yI5iU9a7T-NyyJN/view?usp=sharing
@Component(modules = MainActivityModule.class, dependencies = ProductDataComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    ProductsListAdapter getProductsListAdapter();

    ProductRepositoryImpl getProductRepository();
}
