package com.google.android.gms.internal.measurement;

import android.content.ComponentName;

final class dv implements Runnable {
    private final /* synthetic */ ComponentName a;
    private final /* synthetic */ dt b;

    dv(dt dtVar, ComponentName componentName) {
        this.b = dtVar;
        this.a = componentName;
    }

    public final void run() {
        di.a(this.b.a, this.a);
    }
}
