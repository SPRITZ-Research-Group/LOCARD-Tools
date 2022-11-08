package com.google.android.gms.internal.measurement;

import android.content.Intent;

final /* synthetic */ class ea implements Runnable {
    private final dz a;
    private final int b;
    private final av c;
    private final Intent d;

    ea(dz dzVar, int i, av avVar, Intent intent) {
        this.a = dzVar;
        this.b = i;
        this.c = avVar;
        this.d = intent;
    }

    public final void run() {
        this.a.a(this.b, this.c, this.d);
    }
}
