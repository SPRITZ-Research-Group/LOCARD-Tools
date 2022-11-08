package com.google.android.gms.internal.measurement;

import android.os.Bundle;

final class dc implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ long c;
    private final /* synthetic */ Bundle d;
    private final /* synthetic */ boolean e = true;
    private final /* synthetic */ boolean f;
    private final /* synthetic */ boolean g;
    private final /* synthetic */ String h;
    private final /* synthetic */ cw i;

    dc(cw cwVar, String str, String str2, long j, Bundle bundle, boolean z) {
        this.i = cwVar;
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = bundle;
        this.f = z;
        this.g = false;
        this.h = null;
    }

    public final void run() {
        this.i.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }
}
