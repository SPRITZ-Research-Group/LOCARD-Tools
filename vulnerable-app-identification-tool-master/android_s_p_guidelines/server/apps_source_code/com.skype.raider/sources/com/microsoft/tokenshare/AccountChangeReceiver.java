package com.microsoft.tokenshare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class AccountChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getData() != null ? intent.getData().getSchemeSpecificPart() : null;
        if (packageName != null) {
            c.a.a(context.getApplicationContext(), packageName);
        }
    }

    static IntentFilter a() {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("com.microsoft.tokenshare.SIGNIN_COMPLETED");
        intentfilter.addDataScheme("package");
        return intentfilter;
    }
}
