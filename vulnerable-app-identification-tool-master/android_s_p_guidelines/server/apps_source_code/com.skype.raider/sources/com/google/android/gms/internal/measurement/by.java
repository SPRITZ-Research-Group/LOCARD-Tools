package com.google.android.gms.internal.measurement;

final class by implements Runnable {
    private final /* synthetic */ cv a;
    private final /* synthetic */ bx b;

    by(bx bxVar, cv cvVar) {
        this.b = bxVar;
        this.a = cvVar;
    }

    public final void run() {
        this.b.a();
        this.b.b();
    }
}
