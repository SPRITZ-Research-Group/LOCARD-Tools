package com.google.android.gms.c;

final class n implements Runnable {
    private final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void run() {
        synchronized (this.a.b) {
            if (this.a.c != null) {
                this.a.c.b();
            }
        }
    }
}
