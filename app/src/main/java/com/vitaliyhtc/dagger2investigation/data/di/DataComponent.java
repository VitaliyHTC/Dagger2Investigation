package com.vitaliyhtc.dagger2investigation.data.di;

import android.content.Context;

import com.vitaliyhtc.dagger2investigation.domain.ProductRepository;
import com.vitaliyhtc.dagger2investigation.global.Dagger2InvestigationApp;
import com.vitaliyhtc.dagger2investigation.global.di.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


//TODO: apply new elements here!
@Singleton
@Component(
        modules = {
                AppModule.class,

                DataModule.class,
                RetrofitModule.class
        }
)
public interface DataComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Dagger2InvestigationApp application);

        DataComponent build();
    }

    Context getContext();

    ProductRepository getProductRepository();

}
