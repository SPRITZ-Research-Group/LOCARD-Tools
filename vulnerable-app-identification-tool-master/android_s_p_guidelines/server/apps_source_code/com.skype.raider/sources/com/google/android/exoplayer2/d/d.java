package com.google.android.exoplayer2.d;

public final class d {
    private boolean a;

    public final synchronized boolean a() {
        boolean z = true;
        synchronized (this) {
            if (this.a) {
                z = false;
            } else {
                this.a = true;
                notifyAll();
            }
        }
        return z;
    }

    public final synchronized boolean b() {
        boolean wasOpen;
        wasOpen = this.a;
        this.a = false;
        return wasOpen;
    }

    public final synchronized void c() throws InterruptedException {
        while (!this.a) {
            wait();
        }
    }
}
