package com.vitaliyhtc.dagger2investigation.app;

import android.app.Activity;
import android.app.Application;

import com.vitaliyhtc.dagger2investigation.di.component.DaggerProductDataComponent;
import com.vitaliyhtc.dagger2investigation.di.component.ProductDataComponent;
import com.vitaliyhtc.dagger2investigation.di.module.ContextModule;

public class MainApplication extends Application {

    private ProductDataComponent mProductDataComponent;

    public static MainApplication get(Activity activity) {
        return (MainApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mProductDataComponent = DaggerProductDataComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public ProductDataComponent getProductDataComponent() {
        return mProductDataComponent;
    }
}
