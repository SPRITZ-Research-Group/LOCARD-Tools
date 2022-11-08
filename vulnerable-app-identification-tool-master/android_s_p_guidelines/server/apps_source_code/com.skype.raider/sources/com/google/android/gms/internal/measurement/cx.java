package com.google.android.gms.internal.measurement;

final class cx implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ Object c;
    private final /* synthetic */ long d;
    private final /* synthetic */ cw e;

    cx(cw cwVar, String str, String str2, Object obj, long j) {
        this.e = cwVar;
        this.a = str;
        this.b = str2;
        this.c = obj;
        this.d = j;
    }

    public final void run() {
        cw.a(this.e, this.a, this.b, this.c, this.d);
    }
}
