package com.applovin.impl.sdk;

class bn implements Runnable {
    final /* synthetic */ bm a;

    bn(bm bmVar) {
        this.a = bmVar;
    }

    public void run() {
        if (this.a.c != null) {
            this.a.c.dismiss();
        }
    }
}
