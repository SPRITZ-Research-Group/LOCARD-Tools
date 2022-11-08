package com.google.android.gms.internal.measurement;

final class cr implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ long d;
    private final /* synthetic */ bz e;

    cr(bz bzVar, String str, String str2, String str3, long j) {
        this.e = bzVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j;
    }

    public final void run() {
        if (this.a == null) {
            this.e.a.N().s().a(this.b, null);
            return;
        }
        this.e.a.N().s().a(this.b, new de(this.c, this.a, this.d));
    }
}
