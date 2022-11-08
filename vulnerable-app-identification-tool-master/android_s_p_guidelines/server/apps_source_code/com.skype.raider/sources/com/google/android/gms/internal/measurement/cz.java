package com.google.android.gms.internal.measurement;

import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;

final class cz implements Runnable {
    private final /* synthetic */ ConditionalUserProperty a;
    private final /* synthetic */ cw b;

    cz(cw cwVar, ConditionalUserProperty conditionalUserProperty) {
        this.b = cwVar;
        this.a = conditionalUserProperty;
    }

    public final void run() {
        cw.b(this.b, this.a);
    }
}
