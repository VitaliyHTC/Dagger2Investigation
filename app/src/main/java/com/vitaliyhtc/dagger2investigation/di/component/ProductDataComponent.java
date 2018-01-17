package com.vitaliyhtc.dagger2investigation.di.component;

import com.squareup.picasso.Picasso;
import com.vitaliyhtc.dagger2investigation.data.ProductRepositoryImpl;
import com.vitaliyhtc.dagger2investigation.di.module.PicassoModule;
import com.vitaliyhtc.dagger2investigation.di.module.ProductRepositoryModule;
import com.vitaliyhtc.dagger2investigation.interfaces.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ProductRepositoryModule.class, PicassoModule.class})
public interface ProductDataComponent {

    ProductRepositoryImpl getProductRepository();

    Picasso getPicasso();
}
