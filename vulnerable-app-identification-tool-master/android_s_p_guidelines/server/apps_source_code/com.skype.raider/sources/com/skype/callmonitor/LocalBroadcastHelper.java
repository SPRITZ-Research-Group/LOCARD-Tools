package com.skype.callmonitor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.c;

public class LocalBroadcastHelper {
    public static Intent a(String action) {
        Intent intent = new Intent("callmonitorservice");
        intent.putExtra("actiontype", action);
        return intent;
    }

    public static Intent b(String action) {
        Intent intent = new Intent("callmonitormodule");
        intent.putExtra("actiontype", action);
        return intent;
    }

    public static void a(Context context, Intent intent) {
        c.a(context).a(intent);
    }
}
