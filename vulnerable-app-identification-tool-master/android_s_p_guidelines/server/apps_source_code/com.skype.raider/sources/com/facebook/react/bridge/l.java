package com.facebook.react.bridge;

public abstract class l implements Runnable {
    private final ai a;

    public abstract void a();

    public l(ai reactContext) {
        this.a = reactContext;
    }

    public final void run() {
        try {
            a();
        } catch (RuntimeException e) {
            this.a.a(e);
        }
    }
}
