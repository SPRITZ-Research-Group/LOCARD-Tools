package com.appboy;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import com.appboy.f.c;
import com.appboy.push.e;

public final class AppboyGcmReceiver extends BroadcastReceiver {
    private static final String a = c.a(AppboyGcmReceiver.class);

    public class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ AppboyGcmReceiver a;
        private final Context b;
        private final Intent c;

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        public a(AppboyGcmReceiver this$0, Context context, Intent intent) {
            this.a = this$0;
            this.b = context;
            this.c = intent;
            execute(new Void[0]);
        }

        private Void a() {
            try {
                this.a.a(this.b, this.c);
            } catch (Exception e) {
                c.d(AppboyGcmReceiver.a, "Failed to create and display notification.", e);
            }
            return null;
        }
    }

    public final void onReceive(Context context, Intent intent) {
        c.d(a, "Received broadcast message. Message: " + intent.toString());
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            if (new com.appboy.a.a(context).c()) {
                String stringExtra = intent.getStringExtra("error");
                String stringExtra2 = intent.getStringExtra("registration_id");
                if (stringExtra != null) {
                    if (!"SERVICE_NOT_AVAILABLE".equals(stringExtra) && !"ACCOUNT_MISSING".equals(stringExtra) && !"AUTHENTICATION_FAILED".equals(stringExtra) && !"INVALID_SENDER".equals(stringExtra) && !"PHONE_REGISTRATION_ERROR".equals(stringExtra) && !"INVALID_PARAMETERS".equals(stringExtra)) {
                        c.f(a, "Received an unrecognised GCM registration error type. Ignoring. Error: " + stringExtra);
                    }
                } else if (stringExtra2 != null) {
                    a.a(context).d(stringExtra2);
                } else if (intent.hasExtra("unregistered")) {
                    c.f(a, "The device was un-registered from GCM.");
                } else {
                    c.f(a, "The GCM registration message is missing error information, registration id, and unregistration confirmation. Ignoring.");
                }
            }
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            b(context, intent);
        } else if ("com.appboy.action.CANCEL_NOTIFICATION".equals(action)) {
            e.d(context, intent);
        } else if ("com.appboy.action.APPBOY_ACTION_CLICKED".equals(action)) {
            com.appboy.push.a.a(context, intent);
        } else if ("com.appboy.action.STORY_TRAVERSE".equals(action)) {
            b(context, intent);
        } else if ("com.appboy.action.APPBOY_STORY_CLICKED".equals(action)) {
            e.e(context, intent);
        } else if ("com.appboy.action.APPBOY_PUSH_CLICKED".equals(action)) {
            e.a(context, intent);
        } else if ("com.appboy.action.APPBOY_PUSH_DELETED".equals(action)) {
            e.b(context, intent);
        } else {
            c.f(a, "The GCM receiver received a message not sent from Appboy. Ignoring the message.");
        }
    }

    final boolean a(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if ("deleted_messages".equals(intent.getStringExtra("message_type"))) {
            int totalDeleted = intent.getIntExtra("total_deleted", -1);
            if (totalDeleted == -1) {
                new StringBuilder("Unable to parse GCM message. Intent: ").append(intent.toString());
            } else {
                c.d(a, "GCM deleted " + totalDeleted + " messages. Fetch them from Appboy.");
            }
            return false;
        }
        Bundle gcmExtras = intent.getExtras();
        c.d(a, "Push message payload received: " + gcmExtras);
        Bundle appboyExtras = e.a(gcmExtras);
        gcmExtras.putBundle("extra", appboyExtras);
        if (!gcmExtras.containsKey("appboy_push_received_timestamp")) {
            gcmExtras.putLong("appboy_push_received_timestamp", System.currentTimeMillis());
        }
        if (e.b(intent)) {
            Notification notification;
            Context context2;
            c.b(a, "Received notification push");
            int notificationId = e.b(gcmExtras);
            gcmExtras.putInt("nid", notificationId);
            com.appboy.a.a appConfigurationProvider = new com.appboy.a.a(context);
            i appboyNotificationFactory = e.a();
            if (gcmExtras.containsKey("ab_c")) {
                if (!gcmExtras.containsKey("appboy_story_newly_received")) {
                    c.b(a, "Received the initial push story notification.");
                    gcmExtras.putBoolean("appboy_story_newly_received", true);
                }
                notification = appboyNotificationFactory.a(appConfigurationProvider, context, gcmExtras, appboyExtras);
                if (notification != null) {
                    c.b(a, "Notification created by notification factory was null. Not displaying notification.");
                    return false;
                }
                notificationManager.notify("appboy_notification", notificationId, notification);
                e.a(context, gcmExtras);
                e.c(context, gcmExtras);
                if (gcmExtras != null && gcmExtras.containsKey("nd")) {
                    context2 = context;
                    e.a(context2, getClass(), notificationId, Integer.parseInt(gcmExtras.getString("nd")));
                }
                return true;
            }
            e.e(context, gcmExtras);
            notification = appboyNotificationFactory.a(appConfigurationProvider, context, gcmExtras, appboyExtras);
            if (notification != null) {
                notificationManager.notify("appboy_notification", notificationId, notification);
                e.a(context, gcmExtras);
                e.c(context, gcmExtras);
                context2 = context;
                e.a(context2, getClass(), notificationId, Integer.parseInt(gcmExtras.getString("nd")));
                return true;
            }
            c.b(a, "Notification created by notification factory was null. Not displaying notification.");
            return false;
        }
        c.b(a, "Received data push");
        e.e(context, gcmExtras);
        e.a(context, gcmExtras);
        e.b(context, gcmExtras);
        return false;
    }

    private void b(Context context, Intent intent) {
        if (e.a(intent)) {
            a aVar = new a(this, context, intent);
        }
    }
}
