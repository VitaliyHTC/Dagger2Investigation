package com.vitaliyhtc.dagger2investigation.global.di

import com.vitaliyhtc.dagger2investigation.data.di.DataComponent
import com.vitaliyhtc.dagger2investigation.data.di.DataScope
import com.vitaliyhtc.dagger2investigation.global.Dagger2InvestigationApp
import com.vitaliyhtc.dagger2investigation.presentation.base.di.ActivityBuilder
import com.vitaliyhtc.dagger2investigation.presentation.base.di.FragmentBuilder
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@DataScope
@Component(
        dependencies = [DataComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,

            ActivityBuilder::class,
            FragmentBuilder::class
        ]
)
interface AppComponent {

    fun inject(application: Dagger2InvestigationApp)
}