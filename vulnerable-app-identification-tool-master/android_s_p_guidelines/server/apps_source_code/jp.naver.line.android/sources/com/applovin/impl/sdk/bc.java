package com.applovin.impl.sdk;

class bc implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ba b;

    bc(ba baVar, int i) {
        this.b = baVar;
        this.a = i;
    }

    public void run() {
        this.b.b.failedToReceiveAd(this.a);
    }
}
