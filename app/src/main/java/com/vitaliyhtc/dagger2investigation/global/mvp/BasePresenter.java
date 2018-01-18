package com.vitaliyhtc.dagger2investigation.global.mvp;

public interface BasePresenter<T extends BaseView> {
    void onAttachView(T view);
    void onDetachView();
}
