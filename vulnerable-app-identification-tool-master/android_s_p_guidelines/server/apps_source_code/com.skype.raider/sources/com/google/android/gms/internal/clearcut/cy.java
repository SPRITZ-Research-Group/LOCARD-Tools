package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class cy implements PrivilegedExceptionAction<Unsafe> {
    cy() {
    }

    public final /* synthetic */ Object run() throws Exception {
        Class cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (cls.isInstance(obj)) {
                return (Unsafe) cls.cast(obj);
            }
        }
        return null;
    }
}
