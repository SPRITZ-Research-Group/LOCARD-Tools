package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class db implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ boolean e;
    private final /* synthetic */ cw f;

    db(cw cwVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.f = cwVar;
        this.a = atomicReference;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
    }

    public final void run() {
        this.f.q.t().a(this.a, this.b, this.c, this.d, this.e);
    }
}
