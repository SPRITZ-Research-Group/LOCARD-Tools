package com.facebook.share.internal;

final class t implements Runnable {
    private String a;
    private String b;

    t(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final void run() {
        e.a(this.a, this.b);
    }
}
