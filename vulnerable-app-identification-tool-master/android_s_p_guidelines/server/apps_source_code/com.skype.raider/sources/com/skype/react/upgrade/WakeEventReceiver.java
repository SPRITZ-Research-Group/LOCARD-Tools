package com.skype.react.upgrade;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.facebook.common.logging.FLog;
import com.skype.react.activationExperiment.ActivationExperiment;

public class WakeEventReceiver extends WakefulBroadcastReceiver implements UpgradeConstants {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.QUICKBOOT_POWERON".equals(action)) {
                ActivationExperiment.a(context);
            } else if ("android.intent.action.MY_PACKAGE_REPLACED".equals(action) || "WakeEventReceiver.ACTION_OEM_RTC_WAKE".equals(action)) {
                UpgradeContext.a(context, intent);
            } else {
                FLog.w("WakeEventReceiver", "Received an unhandled intent with action " + intent.getAction());
            }
        }
    }
}
