package com.google.android.gms.internal.measurement;

final class du implements Runnable {
    private final /* synthetic */ an a;
    private final /* synthetic */ dt b;

    du(dt dtVar, an anVar) {
        this.b = dtVar;
        this.a = anVar;
    }

    public final void run() {
        synchronized (this.b) {
            this.b.b = false;
            if (!this.b.a.v()) {
                this.b.a.q().C().a("Connected to service");
                this.b.a.a(this.a);
            }
        }
    }
}
