package com.google.android.gms.c;

final class p implements Runnable {
    private final /* synthetic */ g a;
    private final /* synthetic */ o b;

    p(o oVar, g gVar) {
        this.b = oVar;
        this.a = gVar;
    }

    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                this.b.c.a();
            }
        }
    }
}
