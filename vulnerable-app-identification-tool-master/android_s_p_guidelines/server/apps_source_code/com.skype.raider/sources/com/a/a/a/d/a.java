package com.a.a.a.d;

import java.util.concurrent.atomic.AtomicReference;

public final class a {
    protected final a a = null;
    protected final AtomicReference<b> b;
    protected final boolean c;
    private final int d;

    static final class a {
    }

    private static final class b {
        public final int a = 0;
        public final int b = 63;
        public final int[] c;
        public final c[] d;
        public final a[] e;
        public final int f;
        public final int g;
        public final int h;

        public b(int[] iArr, c[] cVarArr) {
            this.c = iArr;
            this.d = cVarArr;
            this.e = null;
            this.f = 0;
            this.g = 0;
            this.h = 0;
        }
    }

    private a(int i) {
        this.d = i;
        this.c = true;
        this.b = new AtomicReference(new b(new int[64], new c[64]));
    }

    public static a a() {
        long currentTimeMillis = System.currentTimeMillis();
        return new a((((int) (currentTimeMillis >>> 32)) + ((int) currentTimeMillis)) | 1);
    }
}
