package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;

final class fz extends ContentObserver {
    private final /* synthetic */ fy a;

    fz(fy fyVar) {
        this.a = fyVar;
        super(null);
    }

    public final void onChange(boolean z) {
        this.a.b();
        fy.a(this.a);
    }
}
