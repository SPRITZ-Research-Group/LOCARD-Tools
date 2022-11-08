package com.applovin.impl.adview;

class cf implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ cb b;

    cf(cb cbVar, int i) {
        this.b = cbVar;
        this.a = i;
    }

    public void run() {
        if (this.b.h != null) {
            this.b.h.failedToReceiveAd(this.a);
        }
    }
}
