package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

final class cm implements Callable<byte[]> {
    private final /* synthetic */ zzeu a;
    private final /* synthetic */ String b;
    private final /* synthetic */ bz c;

    cm(bz bzVar, zzeu zzeu, String str) {
        this.c = bzVar;
        this.a = zzeu;
        this.b = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.c.a.K();
        return this.c.a.b(this.a, this.b);
    }
}
