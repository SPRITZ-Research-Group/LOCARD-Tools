package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

final class er implements Callable<String> {
    private final /* synthetic */ zzdz a;
    private final /* synthetic */ eo b;

    er(eo eoVar, zzdz zzdz) {
        this.b = eoVar;
        this.a = zzdz;
    }

    public final /* synthetic */ Object call() throws Exception {
        r a = this.b.c().i(this.a.a) ? this.b.e(this.a) : this.b.G().b(this.a.a);
        if (a != null) {
            return a.c();
        }
        this.b.q().y().a("App info was null when attempting to get app instance id");
        return null;
    }
}
