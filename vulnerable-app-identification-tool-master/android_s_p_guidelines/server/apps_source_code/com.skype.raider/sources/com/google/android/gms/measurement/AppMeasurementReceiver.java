package com.google.android.gms.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.measurement.bn;
import com.google.android.gms.internal.measurement.bq;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements bq {
    private bn a;

    public final PendingResult a() {
        return goAsync();
    }

    @MainThread
    public final void a(Context context, Intent intent) {
        WakefulBroadcastReceiver.a_(context, intent);
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (this.a == null) {
            this.a = new bn(this);
        }
        this.a.a(context, intent);
    }
}
