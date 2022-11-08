package com.ad.tracking.cpe.agent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class MyNetChange extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean z;
        boolean z2;
        String string;
        String string2;
        Exception e;
        StringBuilder stringBuilder;
        if (ADTrackingAgent.isNetworkConnected(context)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(ADTConstants.SHARED_PREF_KEY, 4);
            String str = "";
            String str2 = "";
            try {
                z = sharedPreferences.getBoolean(ADTConstants.LAST_REQ_SHOULD_CHECK, false);
                try {
                    z2 = sharedPreferences.getBoolean(ADTConstants.LAST_REQ_IS_DEBUG, false);
                    try {
                        string = sharedPreferences.getString(ADTConstants.LAST_REQ_MERCHANT_ID, "");
                        try {
                            string2 = sharedPreferences.getString(ADTConstants.LAST_REQ_AD_ID, "");
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        string = str;
                        stringBuilder = new StringBuilder("Get Data Failed:");
                        stringBuilder.append(e.getMessage());
                        Log.e("NetworkChange", stringBuilder.toString());
                        string2 = str2;
                        if (!z) {
                            ADTrackingAgent.resetData(context, false);
                            ADTrackingAgent.completed(context, string, string2, Boolean.valueOf(z2));
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    string = str;
                    z2 = false;
                    stringBuilder = new StringBuilder("Get Data Failed:");
                    stringBuilder.append(e.getMessage());
                    Log.e("NetworkChange", stringBuilder.toString());
                    string2 = str2;
                    if (!z) {
                        ADTrackingAgent.resetData(context, false);
                        ADTrackingAgent.completed(context, string, string2, Boolean.valueOf(z2));
                    }
                }
            } catch (Exception e5) {
                e = e5;
                string = str;
                z = false;
                z2 = false;
                stringBuilder = new StringBuilder("Get Data Failed:");
                stringBuilder.append(e.getMessage());
                Log.e("NetworkChange", stringBuilder.toString());
                string2 = str2;
                if (!z) {
                    ADTrackingAgent.resetData(context, false);
                    ADTrackingAgent.completed(context, string, string2, Boolean.valueOf(z2));
                }
            }
            if (!z) {
                ADTrackingAgent.resetData(context, false);
                ADTrackingAgent.completed(context, string, string2, Boolean.valueOf(z2));
            }
        }
    }
}
