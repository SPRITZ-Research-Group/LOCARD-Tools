package com.facebook.internal;

import android.content.Context;

final class af implements Runnable {
    private Context a;
    private ai b;
    private boolean c;

    af(Context context, ai aiVar, boolean z) {
        this.a = context;
        this.b = aiVar;
        this.c = z;
    }

    public final void run() {
        ae.a(this.b, this.c);
    }
}
