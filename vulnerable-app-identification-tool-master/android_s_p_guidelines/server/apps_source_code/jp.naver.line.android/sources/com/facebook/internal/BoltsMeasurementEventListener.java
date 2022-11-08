package com.facebook.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import defpackage.amm;
import defpackage.lj;

public class BoltsMeasurementEventListener extends BroadcastReceiver {
    private static BoltsMeasurementEventListener a;
    private Context b;

    private BoltsMeasurementEventListener(Context context) {
        this.b = context.getApplicationContext();
    }

    public static BoltsMeasurementEventListener a(Context context) {
        if (a != null) {
            return a;
        }
        BroadcastReceiver boltsMeasurementEventListener = new BoltsMeasurementEventListener(context);
        a = boltsMeasurementEventListener;
        lj.a(boltsMeasurementEventListener.b).a(boltsMeasurementEventListener, new IntentFilter("com.parse.bolts.measurement_event"));
        return a;
    }

    public void onReceive(Context context, Intent intent) {
        amm a = amm.a(context);
        StringBuilder stringBuilder = new StringBuilder("bf_");
        stringBuilder.append(intent.getStringExtra("event_name"));
        String stringBuilder2 = stringBuilder.toString();
        Bundle bundleExtra = intent.getBundleExtra("event_args");
        Bundle bundle = new Bundle();
        for (String str : bundleExtra.keySet()) {
            bundle.putString(str.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String) bundleExtra.get(str));
        }
        a.a(stringBuilder2, bundle);
    }

    protected void finalize() throws Throwable {
        try {
            lj.a(this.b).a((BroadcastReceiver) this);
        } finally {
            super.finalize();
        }
    }
}
