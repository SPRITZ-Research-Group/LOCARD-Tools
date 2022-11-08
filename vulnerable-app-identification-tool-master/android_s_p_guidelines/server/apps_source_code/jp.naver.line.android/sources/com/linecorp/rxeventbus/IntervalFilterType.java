package com.linecorp.rxeventbus;

import defpackage.oyn;
import defpackage.oyu;
import java.util.concurrent.TimeUnit;

public enum IntervalFilterType {
    NONE,
    DEBOUNCE,
    SAMPLE_FIRST,
    SAMPLE_LAST;

    public final <T> oyn<T> filter(oyn<T> oyn, int i, oyu oyu) {
        switch (this) {
            case NONE:
                return oyn;
            case DEBOUNCE:
                return oyn.b((long) i, TimeUnit.MILLISECONDS, oyu);
            case SAMPLE_FIRST:
                return oyn.c((long) i, TimeUnit.MILLISECONDS, oyu);
            case SAMPLE_LAST:
                return oyn.d((long) i, TimeUnit.MILLISECONDS, oyu);
            default:
                return oyn;
        }
    }
}
