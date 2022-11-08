package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.c.h;

final /* synthetic */ class ac implements Runnable {
    private final ab a;
    private final Bundle b;
    private final h c;

    ac(ab abVar, Bundle bundle, h hVar) {
        this.a = abVar;
        this.b = bundle;
        this.c = hVar;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
