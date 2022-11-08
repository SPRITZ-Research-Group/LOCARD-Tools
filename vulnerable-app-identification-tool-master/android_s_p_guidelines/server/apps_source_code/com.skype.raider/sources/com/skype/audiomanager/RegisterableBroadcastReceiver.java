package com.skype.audiomanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.common.logging.FLog;

public abstract class RegisterableBroadcastReceiver extends BroadcastReceiver {
    protected final Context a;
    private final String b;
    private boolean c;

    public abstract IntentFilter a();

    public RegisterableBroadcastReceiver(Context c, String t) {
        this.a = c;
        this.b = t;
    }

    public final void f() {
        if (!this.c) {
            Intent stickyIntent = this.a.registerReceiver(this, a());
            if (stickyIntent != null) {
                FLog.i(this.b, "Received sticky intent %s", stickyIntent.getAction());
                onReceive(this.a, stickyIntent);
            }
            FLog.i(this.b, "Registered receiver");
            this.c = true;
        }
    }

    public final void g() {
        if (this.c) {
            this.a.unregisterReceiver(this);
            this.c = false;
        }
    }
}
