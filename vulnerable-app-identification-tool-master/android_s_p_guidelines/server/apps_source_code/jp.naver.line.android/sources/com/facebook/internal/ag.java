package com.facebook.internal;

import android.content.Context;

final class ag implements Runnable {
    private Context a;
    private ai b;

    ag(Context context, ai aiVar) {
        this.a = context;
        this.b = aiVar;
    }

    public final void run() {
        ae.a(this.b);
    }
}
