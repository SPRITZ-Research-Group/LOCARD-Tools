package com.facebook.datasource;

public final class h<T> extends a<T> {
    private h() {
    }

    public static <T> h<T> i() {
        return new h();
    }

    public final boolean a(T value, boolean isLast) {
        return super.a(com.facebook.common.internal.h.a((Object) value), isLast);
    }

    public final boolean b(T value) {
        return super.a(com.facebook.common.internal.h.a((Object) value), true);
    }

    public final boolean a(Throwable throwable) {
        return super.a((Throwable) com.facebook.common.internal.h.a((Object) throwable));
    }

    public final boolean a(float progress) {
        return super.a(progress);
    }
}
