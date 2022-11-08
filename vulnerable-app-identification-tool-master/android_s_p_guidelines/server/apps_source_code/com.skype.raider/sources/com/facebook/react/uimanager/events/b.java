package com.facebook.react.uimanager.events;

import android.os.SystemClock;

public abstract class b<T extends b> {
    private static int a = 0;
    private boolean b;
    private int c;
    private long d;
    private int e;

    public abstract String a();

    public abstract void a(RCTEventEmitter rCTEventEmitter);

    protected b() {
        int i = a;
        a = i + 1;
        this.e = i;
    }

    protected b(int viewTag) {
        int i = a;
        a = i + 1;
        this.e = i;
        a(viewTag);
    }

    protected void a(int viewTag) {
        this.c = viewTag;
        this.d = SystemClock.uptimeMillis();
        this.b = true;
    }

    public final int d() {
        return this.c;
    }

    public final long e() {
        return this.d;
    }

    public boolean b() {
        return true;
    }

    public short f() {
        return (short) 0;
    }

    public void c() {
    }

    final boolean g() {
        return this.b;
    }

    final void h() {
        this.b = false;
        c();
    }

    public final T a(T otherEvent) {
        return this.d >= otherEvent.d ? this : otherEvent;
    }
}
