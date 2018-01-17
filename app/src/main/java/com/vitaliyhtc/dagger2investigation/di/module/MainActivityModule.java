package com.vitaliyhtc.dagger2investigation.di.module;

import com.squareup.picasso.Picasso;
import com.vitaliyhtc.dagger2investigation.MainActivity;
import com.vitaliyhtc.dagger2investigation.interfaces.MainActivityScope;
import com.vitaliyhtc.dagger2investigation.view.adapter.ProductsListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mMainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public ProductsListAdapter productsListAdapter(Picasso picasso) {
        return new ProductsListAdapter(picasso);
    }
}
