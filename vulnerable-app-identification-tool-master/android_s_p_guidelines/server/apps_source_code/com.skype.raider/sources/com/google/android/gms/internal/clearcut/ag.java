package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.ah.e;

final class ag implements bj {
    private static final ag a = new ag();

    private ag() {
    }

    public static ag a() {
        return a;
    }

    public final boolean a(Class<?> cls) {
        return ah.class.isAssignableFrom(cls);
    }

    public final bi b(Class<?> cls) {
        String valueOf;
        if (ah.class.isAssignableFrom(cls)) {
            try {
                return (bi) ah.a(cls.asSubclass(ah.class)).b(e.c);
            } catch (Throwable e) {
                Throwable th = e;
                String str = "Unable to get message info for ";
                valueOf = String.valueOf(cls.getName());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            }
        }
        String str2 = "Unsupported message type: ";
        valueOf = String.valueOf(cls.getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
