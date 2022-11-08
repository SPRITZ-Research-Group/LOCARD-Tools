package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.measurement.bn;
import com.google.android.gms.internal.measurement.bq;

public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements bq {
    private bn a;

    public final PendingResult a() {
        return goAsync();
    }

    public final void a(Context context, Intent intent) {
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (this.a == null) {
            this.a = new bn(this);
        }
        this.a.a(context, intent);
    }
}
