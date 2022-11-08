package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.support.annotation.WorkerThread;

final class ef extends ad {
    private final /* synthetic */ ee a;

    ef(ee eeVar, cu cuVar) {
        this.a = eeVar;
        super(cuVar);
    }

    @WorkerThread
    public final void a() {
        cs csVar = this.a;
        csVar.c();
        csVar.q().C().a("Session started, time", Long.valueOf(csVar.j().b()));
        csVar.r().m.a(false);
        csVar.e().b("auto", "_s", new Bundle());
        csVar.r().n.a(csVar.j().a());
    }
}
