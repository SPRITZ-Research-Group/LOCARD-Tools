package com.a.a.a.d;

public final class b {
    static final b a = new b();
    protected b b;
    protected final boolean c;
    protected final boolean d;
    protected String[] e;
    protected a[] f;
    protected int g;
    protected int h;
    protected int i;
    protected int j;
    protected boolean k;
    private final int l;

    static final class a {
    }

    public static b a() {
        long currentTimeMillis = System.currentTimeMillis();
        int i = (((int) (currentTimeMillis >>> 32)) + ((int) currentTimeMillis)) | 1;
        b bVar = a;
        return new b(bVar.e, bVar.f, bVar.g, i, bVar.j);
    }

    private b() {
        this.d = true;
        this.c = true;
        this.k = true;
        this.l = 0;
        this.j = 0;
        this.e = new String[64];
        this.f = new a[32];
        this.i = 63;
        this.g = 0;
        this.j = 0;
        this.h = 48;
    }

    private b(String[] strArr, a[] aVarArr, int i, int i2, int i3) {
        this.b = null;
        this.d = true;
        this.c = true;
        this.e = strArr;
        this.f = aVarArr;
        this.g = i;
        this.l = i2;
        int length = strArr.length;
        this.h = length - (length >> 2);
        this.i = length - 1;
        this.j = i3;
        this.k = false;
    }
}
