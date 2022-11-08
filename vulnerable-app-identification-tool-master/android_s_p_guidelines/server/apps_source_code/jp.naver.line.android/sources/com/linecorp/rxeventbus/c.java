package com.linecorp.rxeventbus;

import java.lang.reflect.Method;

final class c {
    final Method a;
    final Class<?> b;
    final SubscriberType c;
    final IntervalFilterType d;
    final int e;

    c(Method method, Class<?> cls, SubscriberType subscriberType, IntervalFilterType intervalFilterType, int i) {
        this.a = method;
        this.b = cls;
        this.c = subscriberType;
        this.d = intervalFilterType;
        this.e = i;
    }
}
