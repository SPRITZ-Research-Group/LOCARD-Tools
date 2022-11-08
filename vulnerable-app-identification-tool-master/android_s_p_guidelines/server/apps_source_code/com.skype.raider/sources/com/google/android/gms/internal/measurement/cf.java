package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class cf implements Callable<List<ev>> {
    private final /* synthetic */ zzdz a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ bz d;

    cf(bz bzVar, zzdz zzdz, String str, String str2) {
        this.d = bzVar;
        this.a = zzdz;
        this.b = str;
        this.c = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.d.a.K();
        return this.d.a.G().a(this.a.a, this.b, this.c);
    }
}
