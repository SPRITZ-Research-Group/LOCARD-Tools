package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

final class bp implements Runnable {
    private final /* synthetic */ bx a;
    private final /* synthetic */ long b;
    private final /* synthetic */ Bundle c;
    private final /* synthetic */ Context d;
    private final /* synthetic */ av e;
    private final /* synthetic */ PendingResult f;

    bp(bx bxVar, long j, Bundle bundle, Context context, av avVar, PendingResult pendingResult) {
        this.a = bxVar;
        this.b = j;
        this.c = bundle;
        this.d = context;
        this.e = avVar;
        this.f = pendingResult;
    }

    public final void run() {
        long a = this.a.d().h.a();
        long j = this.b;
        if (a > 0 && (j >= a || j <= 0)) {
            j = a - 1;
        }
        if (j > 0) {
            this.c.putLong("click_timestamp", j);
        }
        this.c.putString("_cis", "referrer broadcast");
        AppMeasurement.getInstance(this.d).logEventInternal("auto", "_cmp", this.c);
        this.e.C().a("Install campaign recorded");
        if (this.f != null) {
            this.f.finish();
        }
    }
}
