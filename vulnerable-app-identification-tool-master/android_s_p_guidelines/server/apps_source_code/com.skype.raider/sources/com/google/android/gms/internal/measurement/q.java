package com.google.android.gms.internal.measurement;

final class q implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ n b;

    q(n nVar, long j) {
        this.b = nVar;
        this.a = j;
    }

    public final void run() {
        this.b.b(this.a);
    }
}
