package com.vitaliyhtc.dagger2investigation.domain;

// TODO: 1/19/18 do you need this?
public interface RxFilter<T> {
    boolean isMeetsCondition(T t);
}
