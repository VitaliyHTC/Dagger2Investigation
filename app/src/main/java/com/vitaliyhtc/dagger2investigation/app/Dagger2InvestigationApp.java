package com.vitaliyhtc.dagger2investigation.app;

import com.vitaliyhtc.dagger2investigation.di.component.DaggerProductDataComponent;
import com.vitaliyhtc.dagger2investigation.di.component.ProductDataComponent;
import com.vitaliyhtc.dagger2investigation.di.module.ContextModule;

public class Dagger2InvestigationApp extends android.app.Application {

    private static Dagger2InvestigationApp sInstance;

    private ProductDataComponent mProductDataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        mProductDataComponent = DaggerProductDataComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public ProductDataComponent getProductDataComponent() {
        return mProductDataComponent;
    }

    public static Dagger2InvestigationApp getInstance() {
        return sInstance;
    }
}
