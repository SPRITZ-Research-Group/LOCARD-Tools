package com.google.android.gms.internal.measurement;

final class ae implements Runnable {
    private final /* synthetic */ cu a;
    private final /* synthetic */ ad b;

    ae(ad adVar, cu cuVar) {
        this.b = adVar;
        this.a = cuVar;
    }

    public final void run() {
        this.a.p();
        if (bs.v()) {
            this.a.p().a((Runnable) this);
            return;
        }
        boolean b = this.b.b();
        this.b.d = 0;
        if (b) {
            this.b.a();
        }
    }
}
