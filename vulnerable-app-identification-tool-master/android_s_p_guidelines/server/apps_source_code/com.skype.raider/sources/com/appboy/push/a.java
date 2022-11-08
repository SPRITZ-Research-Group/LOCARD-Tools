package com.appboy.push;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.d;
import com.appboy.f.c;
import com.appboy.f.f;
import com.appboy.f.i;
import com.brentvatne.react.ReactVideoViewManager;

public class a {
    private static final String a = c.a(a.class);

    @TargetApi(16)
    public static void a(Context context, d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras == null) {
            try {
                c.f(a, "Notification extras were null. Doing nothing.");
            } catch (Exception e) {
                c.d(a, "Caught exception while adding notification action buttons.", e);
            }
        } else if (VERSION.SDK_INT >= 16) {
            for (int actionIndex = 0; !i.c(a(actionIndex, notificationExtras, "ab_a*_a")); actionIndex++) {
                Bundle bundle = new Bundle(notificationExtras);
                String a = a(actionIndex, notificationExtras, "ab_a*_a");
                bundle.putInt("appboy_action_index", actionIndex);
                bundle.putString("appboy_action_type", a);
                bundle.putString("appboy_action_id", a(actionIndex, notificationExtras, "ab_a*_id"));
                bundle.putString("appboy_action_uri", a(actionIndex, notificationExtras, "ab_a*_uri"));
                bundle.putString("appboy_action_use_webview", a(actionIndex, notificationExtras, "ab_a*_use_webview"));
                Intent intent = new Intent("com.appboy.action.APPBOY_ACTION_CLICKED").setClass(context, AppboyNotificationRoutingActivity.class);
                intent.putExtras(bundle);
                android.support.v4.app.NotificationCompat.a.a aVar = new android.support.v4.app.NotificationCompat.a.a(0, a(actionIndex, notificationExtras, "ab_a*_t"), PendingIntent.getActivity(context, f.a(), intent, 134217728));
                aVar.a(new Bundle(bundle));
                notificationBuilder.a(aVar.a());
            }
        }
    }

    @TargetApi(16)
    public static void a(Context context, Intent intent) {
        try {
            String actionType = intent.getStringExtra("appboy_action_type");
            if (i.c(actionType)) {
                c.f(a, "Notification action button type was blank or null. Doing nothing.");
                return;
            }
            int notificationId = intent.getIntExtra("nid", -1);
            com.appboy.a.a(context).a(intent.getStringExtra("cid"), intent.getStringExtra("appboy_action_id"));
            if (actionType.equals("ab_uri") || actionType.equals("ab_open")) {
                e.a(context, notificationId);
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                if (actionType.equals("ab_uri") && intent.getExtras().containsKey("appboy_action_uri")) {
                    intent.putExtra(ReactVideoViewManager.PROP_SRC_URI, intent.getStringExtra("appboy_action_uri"));
                    if (intent.getExtras().containsKey("appboy_action_use_webview")) {
                        intent.putExtra("ab_use_webview", intent.getStringExtra("appboy_action_use_webview"));
                    }
                } else {
                    intent.removeExtra(ReactVideoViewManager.PROP_SRC_URI);
                }
                e.f(context, intent);
                if (new com.appboy.a.a(context).p()) {
                    e.c(context, intent);
                }
            } else if (actionType.equals("ab_none")) {
                e.a(context, notificationId);
            } else {
                c.f(a, "Unknown notification action button clicked. Doing nothing.");
            }
        } catch (Exception e) {
            c.d(a, "Caught exception while handling notification action button click.", e);
        }
    }

    public static String a(int actionIndex, Bundle notificationExtras, String actionFieldKeyTemplate) {
        return a(actionIndex, notificationExtras, actionFieldKeyTemplate, "");
    }

    public static String a(int actionIndex, Bundle notificationExtras, String actionFieldKeyTemplate, String defaultValue) {
        String actionFieldValue = notificationExtras.getString(actionFieldKeyTemplate.replace("*", String.valueOf(actionIndex)));
        return actionFieldValue == null ? defaultValue : actionFieldValue;
    }
}
