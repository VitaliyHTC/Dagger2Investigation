package com.vitaliyhtc.dagger2investigation.presentation.base

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : MvpAppCompatFragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}