package com.vitaliyhtc.dagger2investigation.presentation.base;

import android.content.Context;

import com.arellomobile.mvp.MvpAppCompatFragment;

import dagger.android.support.AndroidSupportInjection;

abstract public class BaseFragment extends MvpAppCompatFragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
