package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class GooglePlayServicesUpdatedReceiver extends BroadcastReceiver {
    private Context a;
    private final a b;

    public static abstract class a {
        public abstract void a();
    }

    public GooglePlayServicesUpdatedReceiver(a aVar) {
        this.b = aVar;
    }

    public final synchronized void a() {
        if (this.a != null) {
            this.a.unregisterReceiver(this);
        }
        this.a = null;
    }

    public final void a(Context context) {
        this.a = context;
    }

    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        Object obj = null;
        if (data != null) {
            obj = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(obj)) {
            this.b.a();
            a();
        }
    }
}
