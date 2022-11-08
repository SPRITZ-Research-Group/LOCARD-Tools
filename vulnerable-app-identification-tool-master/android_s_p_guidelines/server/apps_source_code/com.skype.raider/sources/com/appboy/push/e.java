package com.appboy.push;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.adjust.sdk.Constants;
import com.appboy.AppboyAdmReceiver;
import com.appboy.AppboyGcmReceiver;
import com.appboy.a;
import com.appboy.b;
import com.appboy.b.d;
import com.appboy.f.c;
import com.appboy.f.f;
import com.appboy.f.h;
import com.appboy.f.i;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.support.UriUtils;
import com.brentvatne.react.ReactVideoViewManager;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.camera.imagefilter.ImageFilterManager;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static final String a = c.a(e.class);

    public static void b(Context context, Intent intent) {
        try {
            c.b(a, "Sending notification deleted broadcast");
            b(context, ".intent.APPBOY_PUSH_DELETED", intent.getExtras());
        } catch (Exception e) {
            c.d(a, "Exception occurred attempting to handle notification delete intent.", e);
        }
    }

    public static void c(Context context, Intent intent) {
        Bundle extras = intent.getBundleExtra("extra");
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString("cid", intent.getStringExtra("cid"));
        extras.putString(ImageFilterManager.PROP_SOURCE, "Appboy");
        String deepLink = intent.getStringExtra(ReactVideoViewManager.PROP_SRC_URI);
        if (i.c(deepLink)) {
            context.startActivity(UriUtils.getMainActivityIntent(context, extras));
            return;
        }
        boolean useWebView = "true".equalsIgnoreCase(intent.getStringExtra("ab_use_webview"));
        extras.putString(ReactVideoViewManager.PROP_SRC_URI, deepLink);
        extras.putBoolean("ab_use_webview", useWebView);
        AppboyNavigator.getAppboyNavigator().gotoUri(context, ActionFactory.createUriActionFromUrlString(deepLink, extras, useWebView, d.PUSH));
    }

    public static Bundle a(Bundle notificationExtras) {
        if (notificationExtras == null) {
            return null;
        }
        if (notificationExtras.containsKey("appboy_story_newly_received") && !notificationExtras.getBoolean("appboy_story_newly_received")) {
            return notificationExtras.getBundle("extra");
        }
        if (com.appboy.e.a.booleanValue()) {
            return new Bundle(notificationExtras);
        }
        return a(notificationExtras.getString("extra", "{}"));
    }

    private static Bundle a(String jsonStringDictionary) {
        try {
            Bundle bundle = new Bundle();
            JSONObject json = new JSONObject(jsonStringDictionary);
            Iterator keys = json.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                bundle.putString(key, json.getString(key));
            }
            return bundle;
        } catch (JSONException e) {
            c.d(a, "Unable parse JSON into a bundle.", e);
            return null;
        }
    }

    public static boolean a(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && "true".equals(extras.getString("_ab"));
    }

    public static boolean b(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && extras.containsKey("t") && extras.containsKey("a");
    }

    public static void a(Context context, Bundle notificationExtras) {
        c.b(a, "Sending push message received broadcast");
        b(context, ".intent.APPBOY_PUSH_RECEIVED", notificationExtras);
    }

    public static boolean b(Context context, Bundle notificationExtras) {
        if (!notificationExtras.containsKey("ab_sync_geos")) {
            c.b(a, "Geofence sync key not included in push payload. Not syncing geofences.");
        } else if (Boolean.parseBoolean(notificationExtras.getString("ab_sync_geos"))) {
            b.a(context, true);
            return true;
        } else {
            c.b(a, "Geofence sync key was false. Not syncing geofences.");
        }
        return false;
    }

    public static void a(Context context, Class<?> thisClass, int notificationId, int durationInMillis) {
        Intent cancelIntent = new Intent(context, thisClass);
        cancelIntent.setAction("com.appboy.action.CANCEL_NOTIFICATION");
        cancelIntent.putExtra("nid", notificationId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent, 134217728);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (durationInMillis >= Constants.ONE_SECOND) {
            c.b(a, "Setting Notification duration alarm for " + durationInMillis + " ms");
            alarmManager.set(3, SystemClock.elapsedRealtime() + ((long) durationInMillis), pendingIntent);
        }
    }

    public static int b(Bundle notificationExtras) {
        int notificationId;
        if (notificationExtras == null) {
            c.b(a, "Message without extras bundle received. Using default notification id: ");
            return -1;
        } else if (notificationExtras.containsKey("n")) {
            try {
                notificationId = Integer.parseInt(notificationExtras.getString("n"));
                c.b(a, "Using notification id provided in the message's extras bundle: " + notificationId);
                return notificationId;
            } catch (NumberFormatException e) {
                c.d(a, "Unable to parse notification id provided in the message's extras bundle. Using default notification id instead: -1", e);
                return -1;
            }
        } else {
            notificationId = (notificationExtras.getString("t", "") + notificationExtras.getString("a", "")).hashCode();
            c.b(a, "Message without notification id provided in the extras bundle received. Using a hash of the message: " + notificationId);
            return notificationId;
        }
    }

    @TargetApi(16)
    private static int c(Bundle notificationExtras) {
        if (notificationExtras != null && notificationExtras.containsKey("p")) {
            try {
                Object obj;
                int notificationPriority = Integer.parseInt(notificationExtras.getString("p"));
                if (notificationPriority < -2 || notificationPriority > 2) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj != null) {
                    return notificationPriority;
                }
                c.g(a, "Received invalid notification priority " + notificationPriority);
            } catch (NumberFormatException e) {
                c.d(a, "Unable to parse custom priority. Returning default priority of 0", e);
            }
        }
        return 0;
    }

    public static boolean c(Context context, Bundle notificationExtras) {
        if (!h.a(context, "android.permission.WAKE_LOCK")) {
            return false;
        }
        if (VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = a((NotificationManager) context.getSystemService("notification"), notificationExtras);
            if (notificationChannel == null) {
                c.b(a, "Not waking screen on Android O+ device, could not find notification channel.");
                return false;
            }
            int importance = notificationChannel.getImportance();
            if (importance == 1) {
                c.b(a, "Not acquiring wake-lock for Android O+ notification with importance: " + importance);
                return false;
            }
        } else if (VERSION.SDK_INT >= 16 && c(notificationExtras) == -2) {
            return false;
        }
        WakeLock wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435482, a);
        wakeLock.acquire();
        wakeLock.release();
        return true;
    }

    public static com.appboy.i a() {
        com.appboy.i customAppboyNotificationFactory = a.i();
        if (customAppboyNotificationFactory == null) {
            return b.a();
        }
        return customAppboyNotificationFactory;
    }

    public static void d(Context context, Bundle notificationExtras) {
        if (notificationExtras.containsKey("ab_c") && notificationExtras.getBoolean("appboy_story_newly_received", false)) {
            int count = 0;
            String imageUrl = a.a(0, notificationExtras, "ab_c*_i");
            while (!i.c(imageUrl)) {
                c.a(a, "Pre-fetching bitmap at URL: " + imageUrl);
                a.a(context).g().a(context, imageUrl, com.appboy.b.b.NOTIFICATION_ONE_IMAGE_STORY);
                count++;
                imageUrl = a.a(count, notificationExtras, "ab_c*_i");
            }
            notificationExtras.putBoolean("appboy_story_newly_received", false);
        }
    }

    public static void a(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            c.b(a, "Setting title for notification");
            notificationBuilder.a(notificationExtras.getString("t"));
        }
    }

    public static void b(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            c.b(a, "Setting content for notification");
            notificationBuilder.b(notificationExtras.getString("a"));
        }
    }

    public static void c(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            c.b(a, "Setting ticker for notification");
            notificationBuilder.d(notificationExtras.getString("t"));
        }
    }

    public static void a(Context context, NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        try {
            notificationBuilder.a(a(context, "com.appboy.action.APPBOY_PUSH_CLICKED", notificationExtras));
        } catch (Exception e) {
            c.d(a, "Error setting content intent.", e);
        }
    }

    public static void b(Context context, NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        c.b(a, "Setting delete intent.");
        try {
            notificationBuilder.b(a(context, "com.appboy.action.APPBOY_PUSH_DELETED", notificationExtras));
        } catch (Exception e) {
            c.d(a, "Error setting delete intent.", e);
        }
    }

    public static int a(com.appboy.a.a appConfigurationProvider, NotificationCompat.d notificationBuilder) {
        int smallNotificationIconResourceId = appConfigurationProvider.i();
        if (smallNotificationIconResourceId == 0) {
            c.b(a, "Small notification icon resource was not found. Will use the app icon when displaying notifications.");
            smallNotificationIconResourceId = appConfigurationProvider.u();
        } else {
            c.b(a, "Setting small icon for notification via resource id");
        }
        notificationBuilder.a(smallNotificationIconResourceId);
        return smallNotificationIconResourceId;
    }

    public static void d(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras.containsKey("ab_c")) {
            c.b(a, "Set show when not supported in story push.");
            notificationBuilder.a();
        }
    }

    public static boolean a(Context context, com.appboy.a.a appConfigurationProvider, NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras.containsKey("ab_c")) {
            c.b(a, "Large icon not supported in story push.");
            return false;
        }
        if (notificationExtras != null) {
            try {
                if (notificationExtras.containsKey("ab_li")) {
                    c.b(a, "Setting large icon for notification");
                    notificationBuilder.g = com.appboy.f.b.a(context, Uri.parse(notificationExtras.getString("ab_li")), com.appboy.b.b.NOTIFICATION_LARGE_ICON);
                    return true;
                }
            } catch (Exception e) {
                c.d(a, "Error setting large notification icon", e);
            }
        }
        c.b(a, "Large icon bitmap url not present in extras. Attempting to use resource id instead.");
        int largeNotificationIconResourceId = appConfigurationProvider.j();
        if (largeNotificationIconResourceId != 0) {
            notificationBuilder.g = BitmapFactory.decodeResource(context.getResources(), largeNotificationIconResourceId);
            return true;
        }
        c.b(a, "Large icon resource id not present for notification");
        c.b(a, "Large icon not set for notification");
        return false;
    }

    public static void e(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras == null || !notificationExtras.containsKey("sd")) {
            c.b(a, "Sound key not present in notification extras. Not setting sound for notification.");
            return;
        }
        String soundUri = notificationExtras.getString("sd");
        if (soundUri == null) {
            return;
        }
        if (soundUri.equals("d")) {
            c.b(a, "Setting default sound for notification.");
            notificationBuilder.L.defaults = 1;
            return;
        }
        c.b(a, "Setting sound for notification via uri.");
        notificationBuilder.a(Uri.parse(soundUri));
    }

    public static void f(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 16) {
            return;
        }
        if (notificationExtras == null || !notificationExtras.containsKey("s")) {
            c.b(a, "Summary text not present in notification extras. Not setting summary text for notification.");
            return;
        }
        CharSequence summaryText = notificationExtras.getString("s");
        if (summaryText != null) {
            c.b(a, "Setting summary text for notification");
            notificationBuilder.c(summaryText);
        }
    }

    public static void g(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT >= 16 && notificationExtras != null) {
            c.b(a, "Setting priority for notification");
            notificationBuilder.b(c(notificationExtras));
        }
    }

    public static void a(Context context, NotificationCompat.d notificationBuilder, Bundle notificationExtras, Bundle appboyExtras) {
        if (VERSION.SDK_INT >= 16 && notificationExtras != null) {
            c.b(a, "Setting style for notification");
            notificationBuilder.a(d.a(context, notificationExtras, appboyExtras, notificationBuilder));
        }
    }

    public static void a(com.appboy.a.a appConfigurationProvider, NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 21) {
            return;
        }
        if (notificationExtras == null || !notificationExtras.containsKey("ac")) {
            c.b(a, "Using default accent color for notification");
            notificationBuilder.c(appConfigurationProvider.v());
            return;
        }
        c.b(a, "Using accent color for notification from extras bundle");
        notificationBuilder.c((int) Long.parseLong(notificationExtras.getString("ac")));
    }

    public static void h(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT < 21) {
            c.b(a, "Notification category not supported on this android version. Not setting category for notification.");
        } else if (notificationExtras == null || !notificationExtras.containsKey("ab_ct")) {
            c.b(a, "Category not present in notification extras. Not setting category for notification.");
        } else {
            c.b(a, "Setting category for notification");
            notificationBuilder.a(notificationExtras.getString("ab_ct"));
        }
    }

    public static void i(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        Object obj = 1;
        if (VERSION.SDK_INT < 21) {
            c.b(a, "Notification visibility not supported on this android version. Not setting visibility for notification.");
        } else if (notificationExtras != null && notificationExtras.containsKey("ab_vs")) {
            try {
                int visibility = Integer.parseInt(notificationExtras.getString("ab_vs"));
                if (!(visibility == -1 || visibility == 0 || visibility == 1)) {
                    obj = null;
                }
                if (obj != null) {
                    c.b(a, "Setting visibility for notification");
                    notificationBuilder.d(visibility);
                    return;
                }
                c.g(a, "Received invalid notification visibility " + visibility);
            } catch (Exception e) {
                c.d(a, "Failed to parse visibility from notificationExtras", e);
            }
        }
    }

    public static void b(Context context, com.appboy.a.a appboyConfigurationProvider, NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT >= 21 && notificationExtras != null && notificationExtras.containsKey("ab_pn")) {
            Bundle publicNotificationExtras = a(notificationExtras.getString("ab_pn"));
            NotificationCompat.d publicNotificationBuilder = new NotificationCompat.d(context);
            b(publicNotificationBuilder, publicNotificationExtras);
            a(publicNotificationBuilder, publicNotificationExtras);
            f(publicNotificationBuilder, publicNotificationExtras);
            a(appboyConfigurationProvider, publicNotificationBuilder);
            a(appboyConfigurationProvider, publicNotificationBuilder, publicNotificationExtras);
            notificationBuilder.a(publicNotificationBuilder.e());
        }
    }

    public static void d(Context context, Intent intent) {
        try {
            if (intent.hasExtra("nid")) {
                int notificationId = intent.getIntExtra("nid", -1);
                c.b(a, "Cancelling notification action with id: " + notificationId);
                ((NotificationManager) context.getSystemService("notification")).cancel("appboy_notification", notificationId);
            }
        } catch (Exception e) {
            c.d(a, "Exception occurred handling cancel notification intent.", e);
        }
    }

    public static void a(Context context, int notificationId) {
        try {
            c.b(a, "Cancelling notification action with id: " + notificationId);
            Intent cancelNotificationIntent = new Intent("com.appboy.action.CANCEL_NOTIFICATION").setClass(context, b());
            cancelNotificationIntent.putExtra("nid", notificationId);
            f.a(context, cancelNotificationIntent);
        } catch (Exception e) {
            c.d(a, "Exception occurred attempting to cancel notification.", e);
        }
    }

    public static Class<?> b() {
        if (com.appboy.e.a.booleanValue()) {
            return AppboyAdmReceiver.class;
        }
        return AppboyGcmReceiver.class;
    }

    @SuppressLint({"InlinedApi", "NewApi"})
    public static void c(Context context, com.appboy.a.a appConfigurationProvider, NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            NotificationChannel notificationChannel = a(notificationManager, notificationExtras);
            if (notificationChannel != null) {
                c.b(a, "Using notification channel with id: " + notificationChannel.getId());
                notificationBuilder.c(notificationChannel.getId());
            } else if (notificationChannel == null || notificationChannel.getId().equals("com_appboy_default_notification_channel")) {
                notificationChannel = new NotificationChannel("com_appboy_default_notification_channel", appConfigurationProvider.s(), 3);
                notificationChannel.setDescription(appConfigurationProvider.t());
                notificationManager.createNotificationChannel(notificationChannel);
                notificationBuilder.c("com_appboy_default_notification_channel");
                c.b(a, "Using default notification channel with id: com_appboy_default_notification_channel");
            }
        }
    }

    public static void j(NotificationCompat.d notificationBuilder, Bundle notificationExtras) {
        if (VERSION.SDK_INT >= 26) {
            String extrasBadgeCount = notificationExtras.getString("ab_bc", null);
            if (!i.c(extrasBadgeCount)) {
                try {
                    notificationBuilder.i = Integer.parseInt(extrasBadgeCount);
                } catch (NumberFormatException e) {
                    c.d(a, "Caught exception while setting number on notification.", e);
                }
            }
        }
    }

    public static void e(Context context, Bundle pushExtras) {
        if (pushExtras != null) {
            String campaignId = pushExtras.getString("cid");
            if (i.c(campaignId)) {
                c.b(a, "Could not log push delivery event due to null or blank campaign id in push extras bundle: " + pushExtras);
                return;
            } else {
                a.a(context).b(campaignId);
                return;
            }
        }
        c.b(a, "Could not log push delivery event due to null push extras bundle.");
    }

    public static void e(Context context, Intent intent) {
        try {
            a.a(context).b(intent.getStringExtra("appboy_campaign_id"), intent.getStringExtra("appboy_story_page_id"));
            if (i.c(intent.getStringExtra("appboy_action_uri"))) {
                intent.removeExtra(ReactVideoViewManager.PROP_SRC_URI);
            } else {
                intent.putExtra(ReactVideoViewManager.PROP_SRC_URI, intent.getStringExtra("appboy_action_uri"));
                String useWebviewString = intent.getStringExtra("appboy_action_use_webview");
                if (!i.c(useWebviewString)) {
                    intent.putExtra("ab_use_webview", useWebviewString);
                }
            }
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            f(context, intent);
            if (new com.appboy.a.a(context).p()) {
                c(context, intent);
            }
        } catch (Exception e) {
            c.d(a, "Caught exception while handling story click.", e);
        }
    }

    static String a(Resources resources, int stringResourceId, String defaultString) {
        try {
            return resources.getString(stringResourceId);
        } catch (NotFoundException e) {
            return defaultString;
        }
    }

    static void f(Context context, Intent intent) {
        c.b(a, "Sending notification opened broadcast");
        b(context, ".intent.APPBOY_NOTIFICATION_OPENED", intent.getExtras());
    }

    @TargetApi(26)
    private static NotificationChannel a(NotificationManager notificationManager, Bundle notificationExtras) {
        if (notificationExtras == null) {
            c.b(a, "Notification extras bundle was null. Could not find a valid notification channel");
            return null;
        }
        String channelIdFromExtras = notificationExtras.getString("ab_nc", null);
        if (!i.c(channelIdFromExtras)) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelIdFromExtras);
            if (notificationChannel != null) {
                c.b(a, "Found notification channel in extras with id: " + channelIdFromExtras);
                return notificationChannel;
            }
            c.b(a, "Notification channel from extras is invalid, no channel found with id: " + channelIdFromExtras);
        }
        NotificationChannel defaultNotificationChannel = notificationManager.getNotificationChannel("com_appboy_default_notification_channel");
        if (defaultNotificationChannel != null) {
            return defaultNotificationChannel;
        }
        c.b(a, "Appboy default notification channel does not exist on device.");
        return null;
    }

    private static PendingIntent a(Context context, String action, Bundle notificationExtras) {
        Intent pushActionIntent = new Intent(action).setClass(context, b());
        if (notificationExtras != null) {
            pushActionIntent.putExtras(notificationExtras);
        }
        return PendingIntent.getBroadcast(context, f.a(), pushActionIntent, ErrorDialogData.SUPPRESSED);
    }

    private static void b(Context context, String actionSuffix, Bundle notificationExtras) {
        Intent pushIntent = new Intent(context.getPackageName() + actionSuffix);
        if (notificationExtras != null) {
            pushIntent.putExtras(notificationExtras);
        }
        f.a(context, pushIntent);
    }

    public static void a(Context context, Intent intent) {
        try {
            a.a(context).a(intent);
            f(context, intent);
            if (new com.appboy.a.a(context).p()) {
                c(context, intent);
            }
        } catch (Exception e) {
            c.d(a, "Exception occurred attempting to handle notification opened intent.", e);
        }
    }
}
