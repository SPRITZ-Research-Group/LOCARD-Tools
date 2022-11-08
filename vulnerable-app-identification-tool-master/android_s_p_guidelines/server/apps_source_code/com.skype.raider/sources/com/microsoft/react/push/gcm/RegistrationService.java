package com.microsoft.react.push.gcm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import com.facebook.common.logging.FLog;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import com.microsoft.react.push.PushReceiver;
import com.microsoft.react.push.adm.ADMPushRegistration;
import com.microsoft.react.push.b.a;
import java.io.IOException;

public class RegistrationService extends JobIntentService {
    private static final int j = RegistrationService.class.getSimpleName().hashCode();

    private static void a(Context context, Intent work) {
        JobIntentService.a(context, RegistrationService.class, j, work);
    }

    protected final void a(@NonNull Intent intent) {
        Context context = getApplicationContext();
        String action = intent.getAction();
        if (action == null) {
            FLog.i("RegistrationService", "onHandleIntent: skipping null action");
            return;
        }
        FLog.i("RegistrationService", "onHandleIntent with action: " + action);
        if (action.equals("com.microsoft.react.push.PushConstants.ACTION_REGISTER")) {
            if (a.a(context)) {
                try {
                    int a = GoogleApiAvailability.a().a(context);
                    if (a != 0) {
                        FLog.i("RegistrationService", "Google Play Service not available: " + a);
                        return;
                    }
                    String token = FirebaseInstanceId.getInstance().getToken();
                    Intent intent2 = new Intent("com.microsoft.react.push.PushConstants.ACTION_REGISTER");
                    intent2.setClass(this, PushReceiver.class);
                    intent2.putExtra("com.microsoft.react.push.PushConstants.extra.token", token);
                    sendBroadcast(intent2);
                    FLog.i("RegistrationService", "gcmRegister: Registration successful");
                } catch (SecurityException e) {
                    e.printStackTrace();
                    FLog.e("RegistrationService", "Security Exception: Failed to process action " + e.toString());
                }
            } else if (a.b(context)) {
                ADMPushRegistration.getInstance().register(context);
            } else {
                FLog.i("RegistrationService", "onHandleIntent: cannot detect PNH provider for ACTION_REGISTER");
            }
        } else if (!action.equals("com.microsoft.react.push.PushConstants.ACTION_UNREGISTER")) {
        } else {
            if (a.a(context)) {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                    Intent intent3 = new Intent("com.microsoft.react.push.PushConstants.ACTION_UNREGISTER");
                    intent3.setClass(this, PushReceiver.class);
                    sendBroadcast(intent3);
                    FLog.i("RegistrationService", "gcmUnregister: Un-registration successful");
                } catch (IOException e2) {
                    e2.printStackTrace();
                    FLog.e("RegistrationService", "gcmUnregister: Failed to process action " + e2.toString());
                }
            } else if (a.b(context)) {
                ADMPushRegistration.getInstance().unregister(context);
            } else {
                FLog.i("RegistrationService", "onHandleIntent: cannot detect PNH provider for ACTION_UNREGISTER");
            }
        }
    }

    public static void a(Context context) {
        FLog.i("RegistrationService", "register");
        Intent intent = new Intent(context, RegistrationService.class);
        intent.setAction("com.microsoft.react.push.PushConstants.ACTION_REGISTER");
        a(context, intent);
    }

    public static void b(Context context) {
        FLog.i("RegistrationService", "Unregister");
        Intent intent = new Intent(context, RegistrationService.class);
        intent.setAction("com.microsoft.react.push.PushConstants.ACTION_UNREGISTER");
        a(context, intent);
    }
}
