package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class cp implements Callable<List<ev>> {
    private final /* synthetic */ zzdz a;
    private final /* synthetic */ bz b;

    cp(bz bzVar, zzdz zzdz) {
        this.b = bzVar;
        this.a = zzdz;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.b.a.K();
        return this.b.a.G().a(this.a.a);
    }
}
