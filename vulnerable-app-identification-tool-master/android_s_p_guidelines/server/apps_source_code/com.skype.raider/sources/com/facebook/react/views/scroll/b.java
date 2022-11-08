package com.facebook.react.views.scroll;

import android.os.SystemClock;

public final class b {
    private int a = Integer.MIN_VALUE;
    private int b = Integer.MIN_VALUE;
    private long c = -11;

    public final boolean a(int x, int y) {
        long eventTime = SystemClock.uptimeMillis();
        boolean shouldDispatch = (eventTime - this.c <= 10 && this.a == x && this.b == y) ? false : true;
        this.c = eventTime;
        this.a = x;
        this.b = y;
        return shouldDispatch;
    }
}
