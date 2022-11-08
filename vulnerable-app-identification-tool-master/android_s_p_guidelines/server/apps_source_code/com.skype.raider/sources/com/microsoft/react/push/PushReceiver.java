package com.microsoft.react.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.common.logging.FLog;
import com.microsoft.backgroundexecution.a;

public class PushReceiver extends BroadcastReceiver {
    private static final String a = PushReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        FLog.i(a, "onReceive");
        e.a(context, intent);
        a.a.a(intent, "PushReceiver");
    }
}
