package com.google.android.gms.internal.measurement;

import android.content.ComponentName;

final class dx implements Runnable {
    private final /* synthetic */ dt a;

    dx(dt dtVar) {
        this.a = dtVar;
    }

    public final void run() {
        di.a(this.a.a, new ComponentName(this.a.a.k(), "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
