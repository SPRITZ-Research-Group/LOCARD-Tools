package com.microsoft.react.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.am;
import org.json.JSONException;

public class ScheduledNotificationReceiver extends BroadcastReceiver implements d {
    private static final String b = ScheduledNotificationReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        FLog.i(b, "onReceive");
        String detailsString = intent.getStringExtra("details");
        if (detailsString == null) {
            FLog.i(b, "empty details extra");
        }
        try {
            f.a(context, j.a(detailsString));
        } catch (JSONException e) {
            FLog.e(b, "failed to parse details JSON");
        }
    }

    public static void a(Context context, String id, long fireDate, am details) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, ScheduledNotificationReceiver.class);
        try {
            intent.putExtra("details", j.a(details));
            alarmManager.setExact(0, fireDate, PendingIntent.getBroadcast(context, id.hashCode(), intent, 134217728));
        } catch (JSONException e) {
            FLog.e(b, "failed to convert details to JSON");
        }
    }

    public static void a(Context context, String id) {
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, id.hashCode(), new Intent(context, ScheduledNotificationReceiver.class), 0));
    }
}
