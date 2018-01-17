package com.vitaliyhtc.dagger2investigation.presenter;

import com.vitaliyhtc.dagger2investigation.view.BaseView;

//TODO: refactor to one contract interface?
public interface BasePresenter<T extends BaseView> {
    void onAttachView(T view);
    void onDetachView();
}
