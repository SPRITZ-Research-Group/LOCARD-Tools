package com.applovin.impl.sdk;

class s implements Runnable {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    public void run() {
        if (!this.a.a.isForegroundClickInvalidated()) {
            this.a.e.a(this.a.b, this.a.c, this.a.d, this.a.a);
        }
    }
}
