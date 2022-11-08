package com.applovin.impl.sdk;

import java.util.Map;

class cq implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ cp b;

    cq(cp cpVar, Map map) {
        this.b = cpVar;
        this.a = map;
    }

    public void run() {
        try {
            this.b.e.a(this.a);
            this.b.b.initialize(this.b.e, this.b.c, this.b.c.getInitializationActivity());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
