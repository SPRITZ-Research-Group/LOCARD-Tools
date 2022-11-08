package com.skype.smsmanager.nativesms.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.content.a;
import com.skype.smsmanager.nativesms.SmsMmsLogger;
import com.skype.smsmanager.nativesms.services.SmsRelayCoordinator;
import com.skype.smsmanager.nativesms.services.SmsRelayService;

public class SmsRelayBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            SmsMmsLogger.a("SmsRelayBroadcastReceiver", "onReceive - null intent");
            return;
        }
        String action = intent.getAction();
        SmsMmsLogger.a("SmsRelayBroadcastReceiver", "onReceive() action: " + action);
        if (!"android.intent.action.BOOT_COMPLETED".equals(action)) {
            return;
        }
        if (a.a(context, "android.permission.READ_SMS") != 0) {
            SmsMmsLogger.a("SmsRelayBroadcastReceiver", "Permissions are not granted");
            SmsRelayCoordinator.a();
        } else if (VERSION.SDK_INT >= 26) {
            SmsMmsLogger.a("SmsRelayBroadcastReceiver", "onReceive() - Scheduling Sms/Mms Observer jobs on boot");
            SmsRelayCoordinator.b(true, context);
            SmsRelayCoordinator.b(false, context);
        } else {
            SmsMmsLogger.a("SmsRelayBroadcastReceiver", "onReceive() - Starting SmsRelayService on boot");
            context.startService(new Intent(context, SmsRelayService.class));
        }
    }
}
