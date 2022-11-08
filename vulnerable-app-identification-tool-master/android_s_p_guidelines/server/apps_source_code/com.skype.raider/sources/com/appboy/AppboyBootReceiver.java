package com.appboy;

import a.a.cr;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appboy.f.c;

public class AppboyBootReceiver extends BroadcastReceiver {
    private static final String a = c.a(AppboyBootReceiver.class);

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            c.f(a, "Null intent received. Doing nothing.");
        } else if (context == null) {
            c.f(a, "Null context received for intent " + intent.toString() + ". Doing nothing.");
        } else {
            c.d(a, "Received broadcast message. Message: " + intent.toString());
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                c.d(a, "Boot complete intent received. Initializing.");
                cr.a(context);
                a.a(context);
                return;
            }
            c.f(a, "Unknown intent " + intent.toString() + " received. Doing nothing.");
        }
    }
}
