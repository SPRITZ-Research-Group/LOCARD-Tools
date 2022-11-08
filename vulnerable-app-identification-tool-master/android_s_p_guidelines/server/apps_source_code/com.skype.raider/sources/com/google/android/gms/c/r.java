package com.google.android.gms.c;

final class r implements Runnable {
    private final /* synthetic */ g a;
    private final /* synthetic */ q b;

    r(q qVar, g gVar) {
        this.b = qVar;
        this.a = gVar;
    }

    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                this.b.c.a(this.a.e());
            }
        }
    }
}
