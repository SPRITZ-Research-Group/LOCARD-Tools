package com.facebook.share.internal;

import com.facebook.share.widget.e;

final class g implements Runnable {
    private String a;
    private e b;
    private h c;

    g(String str, e eVar, h hVar) {
        this.a = str;
        this.b = eVar;
        this.c = hVar;
    }

    public final void run() {
        e.b(this.a, this.b, this.c);
    }
}
