package com.facebook.ads.internal.p.a;

public enum j {
    GET(false),
    POST(true);
    
    private boolean c;
    private boolean d;

    private j(boolean z) {
        this.c = true;
        this.d = z;
    }

    public final boolean a() {
        return this.c;
    }

    public final boolean b() {
        return this.d;
    }
}
