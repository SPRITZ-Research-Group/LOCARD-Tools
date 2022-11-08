package com.facebook.common.internal;

import javax.annotation.Nullable;

public final class l {
    private static <X extends Throwable> void a(@Nullable Throwable throwable, Class<X> declaredType) throws Throwable {
        if (throwable != null && declaredType.isInstance(throwable)) {
            throw ((Throwable) declaredType.cast(throwable));
        }
    }

    public static void a(@Nullable Throwable throwable) {
        a(throwable, Error.class);
        a(throwable, RuntimeException.class);
    }

    public static RuntimeException b(Throwable throwable) {
        a((Throwable) h.a((Object) throwable));
        throw new RuntimeException(throwable);
    }
}
