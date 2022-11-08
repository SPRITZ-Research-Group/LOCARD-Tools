package com.ad.tracking.cpe.agent;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class ReferrerCatcher extends BroadcastReceiver {
    private static String from = "";

    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.ad.tracking.cpe.agent.ReferrerCatcher"), 128).metaData;
            if (bundle != null) {
                for (String string : bundle.keySet()) {
                    ((BroadcastReceiver) Class.forName(bundle.getString(string)).newInstance()).onReceive(context, intent);
                    Log.i("Referrer", String.format("PASS REFERRER TO...%s", new Object[]{string}));
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("PASS REFERRER error : ");
            stringBuilder.append(e.getMessage());
            Log.e("Referrer", stringBuilder.toString());
        }
        String str = "";
        Bundle extras = intent.getExtras();
        if (extras != null) {
            str = extras.getString("referrer");
            from = extras.getString("from");
        }
        StringBuilder stringBuilder2 = new StringBuilder("CPE Referrer is : ");
        stringBuilder2.append(str);
        stringBuilder2.append(", from : ");
        stringBuilder2.append(from);
        SharedPreferences sharedPreferences = context.getSharedPreferences(ADTConstants.SHARED_PREF_KEY, 4);
        sharedPreferences.edit().putString(ADTConstants.REFERRER_ID, str).apply();
        if (sharedPreferences.getBoolean("completed_called", false)) {
            ADTrackingAgent.completed(context, sharedPreferences.getString(ADTConstants.LAST_REQ_MERCHANT_ID, ""), sharedPreferences.getString(ADTConstants.LAST_REQ_AD_ID, str), Boolean.valueOf(sharedPreferences.getBoolean(ADTConstants.LAST_REQ_IS_DEBUG, true)));
        }
    }
}
