package com.google.android.gms.c;

final class t implements Runnable {
    private final /* synthetic */ g a;
    private final /* synthetic */ s b;

    t(s sVar, g gVar) {
        this.b = sVar;
        this.a = gVar;
    }

    public final void run() {
        synchronized (this.b.b) {
            if (this.b.c != null) {
                e b = this.b.c;
                this.a.d();
                b.a();
            }
        }
    }
}
