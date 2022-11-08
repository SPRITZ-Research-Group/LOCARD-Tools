package com.facebook.android.a.a;

import com.facebook.crypto.b;
import com.facebook.crypto.d.c;

public final class a extends b {
    private static a c;

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    private a() {
        super(new c(), new b());
    }
}
