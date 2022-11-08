package com.skype.campaignreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.adjust.sdk.Constants;

public class CampaignReceiver extends BroadcastReceiver {
    private static String a = "";

    public static String a() {
        return a;
    }

    public void onReceive(Context context, Intent intent) {
        a = intent.getExtras().getString(Constants.REFERRER);
    }
}
