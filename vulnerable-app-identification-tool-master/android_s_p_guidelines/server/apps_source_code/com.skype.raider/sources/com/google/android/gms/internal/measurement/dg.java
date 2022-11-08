package com.google.android.gms.internal.measurement;

import android.os.Bundle;

final class dg implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ de b;
    private final /* synthetic */ de c;
    private final /* synthetic */ df d;

    dg(df dfVar, boolean z, de deVar, de deVar2) {
        this.d = dfVar;
        this.a = z;
        this.b = deVar;
        this.c = deVar2;
    }

    public final void run() {
        if (this.a && this.d.a != null) {
            df.a(this.d, this.d.a);
        }
        boolean z = (this.b != null && this.b.c == this.c.c && ew.b(this.b.b, this.c.b) && ew.b(this.b.a, this.c.a)) ? false : true;
        if (z) {
            Bundle bundle = new Bundle();
            df.a(this.c, bundle, true);
            if (this.b != null) {
                if (this.b.a != null) {
                    bundle.putString("_pn", this.b.a);
                }
                bundle.putString("_pc", this.b.b);
                bundle.putLong("_pi", this.b.c);
            }
            this.d.e().b("auto", "_vs", bundle);
        }
        this.d.a = this.c;
        this.d.h().a(this.c);
    }
}
