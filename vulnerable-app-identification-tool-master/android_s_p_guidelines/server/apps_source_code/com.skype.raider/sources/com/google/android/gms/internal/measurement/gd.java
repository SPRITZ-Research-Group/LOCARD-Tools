package com.google.android.gms.internal.measurement;

final /* synthetic */ class gd implements gj {
    private final String a;
    private final boolean b = false;

    gd(String str) {
        this.a = str;
    }

    public final Object a() {
        return Boolean.valueOf(fw.a(ga.c.getContentResolver(), this.a, this.b));
    }
}
