package com.google.android.gms.internal.measurement;

final class dw implements Runnable {
    private final /* synthetic */ an a;
    private final /* synthetic */ dt b;

    dw(dt dtVar, an anVar) {
        this.b = dtVar;
        this.a = anVar;
    }

    public final void run() {
        synchronized (this.b) {
            this.b.b = false;
            if (!this.b.a.v()) {
                this.b.a.q().B().a("Connected to remote service");
                this.b.a.a(this.a);
            }
        }
    }
}
