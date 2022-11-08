package com.applovin.impl.sdk;

import android.webkit.WebView;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

class ai implements Runnable {
    final /* synthetic */ AtomicReference a;
    final /* synthetic */ CountDownLatch b;
    final /* synthetic */ ah c;

    ai(ah ahVar, AtomicReference atomicReference, CountDownLatch countDownLatch) {
        this.c = ahVar;
        this.a = atomicReference;
        this.b = countDownLatch;
    }

    public void run() {
        try {
            this.a.set(new WebView(this.c.c).getSettings().getUserAgentString());
        } catch (Throwable th) {
            this.b.countDown();
        }
        this.b.countDown();
    }
}
