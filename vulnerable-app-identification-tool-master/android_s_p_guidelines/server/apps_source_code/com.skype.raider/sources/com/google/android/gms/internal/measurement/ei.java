package com.google.android.gms.internal.measurement;

final class ei implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ ee b;

    ei(ee eeVar, long j) {
        this.b = eeVar;
        this.a = j;
    }

    public final void run() {
        ee.b(this.b, this.a);
    }
}
