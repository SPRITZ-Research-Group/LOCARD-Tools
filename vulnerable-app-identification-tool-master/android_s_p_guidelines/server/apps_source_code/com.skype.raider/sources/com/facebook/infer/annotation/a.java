package com.facebook.infer.annotation;

import javax.annotation.Nullable;

public final class a {
    public static <T> T a(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new AssertionError();
    }

    public static <T> T a(@Nullable T t, String str) {
        if (t != null) {
            return t;
        }
        throw new AssertionError(str);
    }

    public static void a(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    public static void a(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }
}
