package com.vitaliyhtc.dagger2investigation.domain;

public interface RxFilter<T> {
    boolean isMeetsCondition(T t);
}
