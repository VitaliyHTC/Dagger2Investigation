package com.vitaliyhtc.dagger2investigation.presentation.base.mvp;

public interface BasePresenter<T extends BaseView> {
    void onAttachView(T view);
    void onDetachView();
}
