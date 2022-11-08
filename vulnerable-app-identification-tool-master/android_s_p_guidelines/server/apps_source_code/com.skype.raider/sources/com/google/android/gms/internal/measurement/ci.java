package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class ci implements Callable<List<zzed>> {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ bz d;

    ci(bz bzVar, String str, String str2, String str3) {
        this.d = bzVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.d.a.K();
        return this.d.a.G().b(this.a, this.b, this.c);
    }
}
