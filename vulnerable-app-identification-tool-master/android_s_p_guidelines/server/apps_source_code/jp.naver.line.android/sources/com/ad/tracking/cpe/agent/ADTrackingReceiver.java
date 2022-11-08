package com.ad.tracking.cpe.agent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class ADTrackingReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null && action.equals("com.ad.tracking.cpe.agent.COMPLETED")) {
                ADTLogUtil.v("ADTrackingReceiver onReceive : ".concat(String.valueOf(action)));
                CharSequence string = intent.getExtras().getString("sim");
                if (!TextUtils.isEmpty(string)) {
                    ADTLogUtil.v("ADTrackingReceiver onReceive sim serial : ".concat(String.valueOf(string)));
                    Editor edit = context.getSharedPreferences(ADTConstants.SHARED_PREF_KEY, 4).edit();
                    edit.putBoolean(ADTConstants.INTENT_REQUEST_SUCCESS, true);
                    edit.apply();
                }
            }
        }
    }
}
