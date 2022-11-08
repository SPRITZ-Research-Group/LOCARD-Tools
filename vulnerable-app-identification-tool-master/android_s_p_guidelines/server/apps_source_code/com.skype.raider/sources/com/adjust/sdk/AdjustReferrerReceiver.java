package com.adjust.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AdjustReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String rawReferrer = intent.getStringExtra(Constants.REFERRER);
        if (rawReferrer != null) {
            Adjust.getDefaultInstance().sendReferrer(rawReferrer, context);
        }
    }
}
