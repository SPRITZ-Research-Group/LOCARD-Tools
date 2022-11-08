package com.google.android.gms.internal.measurement;

final class eh implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ ee b;

    eh(ee eeVar, long j) {
        this.b = eeVar;
        this.a = j;
    }

    public final void run() {
        ee.a(this.b, this.a);
    }
}
