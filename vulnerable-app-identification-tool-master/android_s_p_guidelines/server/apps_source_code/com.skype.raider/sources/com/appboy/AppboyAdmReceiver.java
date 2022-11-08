package com.appboy;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.appboy.f.c;
import com.appboy.push.e;

public final class AppboyAdmReceiver extends BroadcastReceiver {
    private static final String a = c.a(AppboyAdmReceiver.class);

    public class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ AppboyAdmReceiver a;
        private final Context b;
        private final Intent c;

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        public a(AppboyAdmReceiver this$0, Context context, Intent intent) {
            this.a = this$0;
            this.b = context;
            this.c = intent;
            execute(new Void[0]);
        }

        private Void a() {
            try {
                this.a.a(this.b, this.c);
            } catch (Exception e) {
                c.d(AppboyAdmReceiver.a, "Failed to create and display notification.", e);
            }
            return null;
        }
    }

    public final void onReceive(Context context, Intent intent) {
        c.d(a, "Received broadcast message. Message: " + intent.toString());
        String action = intent.getAction();
        if ("com.amazon.device.messaging.intent.REGISTRATION".equals(action)) {
            com.appboy.a.a aVar = new com.appboy.a.a(context);
            c.d(a, "Received ADM registration. Message: " + intent.toString());
            if (aVar.d()) {
                c.b(a, "ADM enabled in appboy.xml. Continuing to process ADM registration intent.");
                String stringExtra = intent.getStringExtra("error");
                String stringExtra2 = intent.getStringExtra("registration_id");
                String stringExtra3 = intent.getStringExtra("unregistered");
                if (stringExtra != null) {
                    c.g(a, "Error during ADM registration: " + stringExtra);
                    return;
                } else if (stringExtra2 != null) {
                    c.d(a, "Registering for ADM messages with registrationId: " + stringExtra2);
                    a.a(context).d(stringExtra2);
                    return;
                } else if (stringExtra3 != null) {
                    c.f(a, "The device was un-registered from ADM: " + stringExtra3);
                    return;
                } else {
                    c.f(a, "The ADM registration intent is missing error information, registration id, and unregistration confirmation. Ignoring.");
                    return;
                }
            }
            c.f(a, "ADM not enabled in appboy.xml. Ignoring ADM registration intent. Note: you must set com_appboy_push_adm_messaging_registration_enabled to true in your appboy.xml to enable ADM.");
        } else if ("com.amazon.device.messaging.intent.RECEIVE".equals(action)) {
            if (e.a(intent)) {
                a aVar2 = new a(this, context, intent);
            }
        } else if ("com.appboy.action.CANCEL_NOTIFICATION".equals(action)) {
            e.d(context, intent);
        } else if ("com.appboy.action.APPBOY_ACTION_CLICKED".equals(action)) {
            com.appboy.push.a.a(context, intent);
        } else if ("com.appboy.action.APPBOY_PUSH_CLICKED".equals(action)) {
            e.a(context, intent);
        } else if ("com.appboy.action.APPBOY_PUSH_DELETED".equals(action)) {
            e.b(context, intent);
        } else {
            c.f(a, "The ADM receiver received a message not sent from Appboy. Ignoring the message.");
        }
    }

    final boolean a(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if ("deleted_messages".equals(intent.getStringExtra("message_type"))) {
            int totalDeleted = intent.getIntExtra("total_deleted", -1);
            if (totalDeleted == -1) {
                c.g(a, "Unable to parse ADM message. Intent: " + intent.toString());
            } else {
                c.d(a, "ADM deleted " + totalDeleted + " messages. Fetch them from Appboy.");
            }
            return false;
        }
        Bundle admExtras = intent.getExtras();
        c.b(a, "Push message payload received: " + admExtras);
        if (!admExtras.containsKey("appboy_push_received_timestamp")) {
            admExtras.putLong("appboy_push_received_timestamp", System.currentTimeMillis());
        }
        e.e(context, admExtras);
        Bundle appboyExtras = e.a(admExtras);
        admExtras.putBundle("extra", appboyExtras);
        if (e.b(intent)) {
            int notificationId = e.b(admExtras);
            admExtras.putInt("nid", notificationId);
            Notification notification = e.a().a(new com.appboy.a.a(context), context, admExtras, appboyExtras);
            if (notification == null) {
                c.b(a, "Notification created by notification factory was null. Not displaying notification.");
                return false;
            }
            notificationManager.notify("appboy_notification", notificationId, notification);
            e.a(context, admExtras);
            e.c(context, admExtras);
            if (admExtras.containsKey("nd")) {
                e.a(context, getClass(), notificationId, Integer.parseInt(admExtras.getString("nd")));
            }
            return true;
        }
        e.a(context, admExtras);
        e.b(context, admExtras);
        return false;
    }
}
