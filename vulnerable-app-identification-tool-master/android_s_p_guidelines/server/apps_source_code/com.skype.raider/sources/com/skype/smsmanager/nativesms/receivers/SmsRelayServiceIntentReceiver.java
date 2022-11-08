package com.skype.smsmanager.nativesms.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.SmsMmsManager;

public class SmsRelayServiceIntentReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            SmsMmsLogger.a("SmsRelayServiceIntentReceiver", "onReceive - null intent");
            return;
        }
        String action = intent.getAction();
        SmsMmsLogger.a("SmsRelayServiceIntentReceiver", "onReceive() action: " + action);
        if ("ACTION_SMSMMS_STATUS".equals(action)) {
            intent.putExtra("outgoingSmsStatus", getResultCode());
            SmsMmsLogger.a("SmsRelayServiceIntentReceiver", "onReceive() OUTGOING_SMS_STATUS: " + getResultCode());
            SmsMmsManager.a(context, intent);
        } else if ("ACTION_SMSMMS_SERVICE".equals(action)) {
            SmsMmsManager.a(context, intent);
        } else if ("ACTION_SMS_NATIVE_TELEMETRY".equals(action)) {
            SmsMmsManager.a(intent);
        }
    }
}
