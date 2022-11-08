package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.c;
import android.support.v4.content.a;
import android.text.TextUtils;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.common.util.m;
import com.google.firebase.iid.o;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class d {
    private static d a;
    private final Context b;
    private Bundle c;
    private Method d;
    private Method e;
    private final AtomicInteger f = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private d(Context context) {
        this.b = context.getApplicationContext();
    }

    @TargetApi(26)
    private final Notification a(CharSequence charSequence, String str, int i, Integer num, Uri uri, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str2) {
        Builder smallIcon = new Builder(this.b).setAutoCancel(true).setSmallIcon(i);
        if (!TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (!TextUtils.isEmpty(str)) {
            smallIcon.setContentText(str);
            smallIcon.setStyle(new BigTextStyle().bigText(str));
        }
        if (num != null) {
            smallIcon.setColor(num.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (pendingIntent2 != null) {
            smallIcon.setDeleteIntent(pendingIntent2);
        }
        if (str2 != null) {
            if (this.d == null) {
                this.d = a("setChannelId");
            }
            if (this.d == null) {
                this.d = a("setChannel");
            }
            if (this.d != null) {
                try {
                    this.d.invoke(smallIcon, new Object[]{str2});
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e2) {
                } catch (SecurityException e3) {
                } catch (IllegalArgumentException e4) {
                }
            }
        }
        return smallIcon.build();
    }

    private final Bundle a() {
        if (this.c != null) {
            return this.c;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.b.getPackageManager().getApplicationInfo(this.b.getPackageName(), 128);
        } catch (NameNotFoundException e) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.c = applicationInfo.metaData;
        return this.c;
    }

    static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    static String a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    @TargetApi(26)
    private static Method a(String str) {
        try {
            return Builder.class.getMethod(str, new Class[]{String.class});
        } catch (NoSuchMethodException e) {
        } catch (SecurityException e2) {
        }
        return null;
    }

    private static void a(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    @TargetApi(26)
    private final boolean a(int i) {
        if (VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.b.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            new StringBuilder(77).append("Adaptive icons cannot be used in notifications. Ignoring icon id: ").append(i);
            return false;
        } catch (NotFoundException e) {
            return false;
        }
    }

    private final Integer b(String str) {
        Integer num = null;
        if (VERSION.SDK_INT < 21) {
            return num;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.");
            }
        }
        int i = a().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i == 0) {
            return num;
        }
        try {
            return Integer.valueOf(a.c(this.b, i));
        } catch (NotFoundException e2) {
            return num;
        }
    }

    private static Object[] b(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String a = a(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(a);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException e) {
            valueOf = String.valueOf(str);
            valueOf2 = String.valueOf("_loc_args");
            valueOf2 = (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).substring(6);
            new StringBuilder((String.valueOf(valueOf2).length() + 41) + String.valueOf(a).length()).append("Malformed ").append(valueOf2).append(": ").append(a).append("  Default value will be used.");
            return null;
        }
    }

    private final String c(Bundle bundle, String str) {
        Object a = a(bundle, str);
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        valueOf = a(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(valueOf)) {
            return null;
        }
        Resources resources = this.b.getResources();
        int identifier = resources.getIdentifier(valueOf, "string", this.b.getPackageName());
        if (identifier == 0) {
            String valueOf3 = String.valueOf(str);
            valueOf2 = String.valueOf("_loc_key");
            valueOf2 = (valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3)).substring(6);
            new StringBuilder((String.valueOf(valueOf2).length() + 49) + String.valueOf(valueOf).length()).append(valueOf2).append(" resource not found: ").append(valueOf).append(" Default value will be used.");
            return null;
        }
        Object[] b = b(bundle, str);
        if (b == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, b);
        } catch (MissingFormatArgumentException e) {
            valueOf2 = Arrays.toString(b);
            new StringBuilder((String.valueOf(valueOf).length() + 58) + String.valueOf(valueOf2).length()).append("Missing format argument for ").append(valueOf).append(": ").append(valueOf2).append(" Default value will be used.");
            return null;
        }
    }

    @TargetApi(26)
    private final String c(String str) {
        if (!m.d()) {
            return null;
        }
        NotificationManager notificationManager = (NotificationManager) this.b.getSystemService(NotificationManager.class);
        try {
            if (this.e == null) {
                this.e = notificationManager.getClass().getMethod("getNotificationChannel", new Class[]{String.class});
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.e.invoke(notificationManager, new Object[]{str}) != null) {
                    return str;
                }
                new StringBuilder(String.valueOf(str).length() + 122).append("Notification Channel requested (").append(str).append(") has not been created by the app. Manifest configuration, or default, value will be used.");
            }
            Object string = a().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (!TextUtils.isEmpty(string)) {
                if (this.e.invoke(notificationManager, new Object[]{string}) != null) {
                    return string;
                }
            }
            if (this.e.invoke(notificationManager, new Object[]{"fcm_fallback_notification_channel"}) == null) {
                Object newInstance = Class.forName("android.app.NotificationChannel").getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{"fcm_fallback_notification_channel", this.b.getString(b.a.fcm_fallback_notification_channel_label), Integer.valueOf(3)});
                notificationManager.getClass().getMethod("createNotificationChannel", new Class[]{r2}).invoke(notificationManager, new Object[]{newInstance});
            }
            return "fcm_fallback_notification_channel";
        } catch (InstantiationException e) {
            return null;
        } catch (InvocationTargetException e2) {
            return null;
        } catch (NoSuchMethodException e3) {
            return null;
        } catch (IllegalAccessException e4) {
            return null;
        } catch (ClassNotFoundException e5) {
            return null;
        } catch (SecurityException e6) {
            return null;
        } catch (IllegalArgumentException e7) {
            return null;
        } catch (LinkageError e8) {
            return null;
        }
    }

    final boolean a(Bundle bundle) {
        if ("1".equals(a(bundle, "gcm.n.noui"))) {
            return true;
        }
        int i;
        CharSequence c;
        CharSequence c2;
        String a;
        Resources resources;
        int identifier;
        Integer b;
        Uri uri;
        Object a2;
        Object a3;
        Uri parse;
        Intent intent;
        Intent intent2;
        Parcelable parcelable;
        Bundle bundle2;
        Intent intent3;
        PendingIntent a4;
        PendingIntent a5;
        Parcelable a42;
        android.support.v4.app.NotificationCompat.d a6;
        Notification e;
        String a7;
        NotificationManager notificationManager;
        if (!((KeyguardManager) this.b.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            if (!m.c()) {
                SystemClock.sleep(10);
            }
            int myPid = Process.myPid();
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.b.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == myPid) {
                        i = runningAppProcessInfo.importance == 100 ? 1 : 0;
                        if (i != 0) {
                            return false;
                        }
                        c = c(bundle, "gcm.n.title");
                        if (TextUtils.isEmpty(c)) {
                            c = this.b.getApplicationInfo().loadLabel(this.b.getPackageManager());
                        }
                        c2 = c(bundle, "gcm.n.body");
                        a = a(bundle, "gcm.n.icon");
                        if (!TextUtils.isEmpty(a)) {
                            resources = this.b.getResources();
                            identifier = resources.getIdentifier(a, "drawable", this.b.getPackageName());
                            if (identifier == 0 || !a(identifier)) {
                                identifier = resources.getIdentifier(a, "mipmap", this.b.getPackageName());
                                if (identifier == 0 || !a(identifier)) {
                                    new StringBuilder(String.valueOf(a).length() + 61).append("Icon resource ").append(a).append(" not found. Notification will use default icon.");
                                }
                            }
                            b = b(a(bundle, "gcm.n.color"));
                            a = a(bundle, "gcm.n.sound2");
                            if (TextUtils.isEmpty(a)) {
                                a = a(bundle, "gcm.n.sound");
                            }
                            if (TextUtils.isEmpty(a)) {
                                uri = null;
                            } else if (!"default".equals(a) || this.b.getResources().getIdentifier(a, "raw", this.b.getPackageName()) == 0) {
                                uri = RingtoneManager.getDefaultUri(2);
                            } else {
                                String packageName = this.b.getPackageName();
                                uri = Uri.parse(new StringBuilder((String.valueOf(packageName).length() + 24) + String.valueOf(a).length()).append("android.resource://").append(packageName).append("/raw/").append(a).toString());
                            }
                            a2 = a(bundle, "gcm.n.click_action");
                            if (TextUtils.isEmpty(a2)) {
                                a3 = a(bundle, "gcm.n.link_android");
                                if (TextUtils.isEmpty(a3)) {
                                    a3 = a(bundle, "gcm.n.link");
                                }
                                parse = TextUtils.isEmpty(a3) ? Uri.parse(a3) : null;
                                if (parse != null) {
                                    intent = new Intent("android.intent.action.VIEW");
                                    intent.setPackage(this.b.getPackageName());
                                    intent.setData(parse);
                                    intent2 = intent;
                                } else {
                                    intent2 = this.b.getPackageManager().getLaunchIntentForPackage(this.b.getPackageName());
                                }
                            } else {
                                intent = new Intent(a2);
                                intent.setPackage(this.b.getPackageName());
                                intent.setFlags(ErrorDialogData.BINDER_CRASH);
                                intent2 = intent;
                            }
                            if (intent2 == null) {
                                parcelable = null;
                            } else {
                                intent2.addFlags(67108864);
                                bundle2 = new Bundle(bundle);
                                FirebaseMessagingService.a(bundle2);
                                intent2.putExtras(bundle2);
                                for (String a8 : bundle2.keySet()) {
                                    if (!a8.startsWith("gcm.n.") || a8.startsWith("gcm.notification.")) {
                                        intent2.removeExtra(a8);
                                    }
                                }
                                parcelable = PendingIntent.getActivity(this.b, this.f.incrementAndGet(), intent2, ErrorDialogData.SUPPRESSED);
                            }
                            if (FirebaseMessagingService.b(bundle)) {
                                intent3 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                a(intent3, bundle);
                                intent3.putExtra("pending_intent", parcelable);
                                a42 = o.a(this.b, this.f.incrementAndGet(), intent3);
                                intent = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                a(intent, bundle);
                                a5 = o.a(this.b, this.f.incrementAndGet(), intent);
                            } else {
                                a5 = null;
                                a42 = parcelable;
                            }
                            if (m.d() || this.b.getApplicationInfo().targetSdkVersion <= 25) {
                                a6 = new android.support.v4.app.NotificationCompat.d(this.b).b().a(identifier);
                                if (!TextUtils.isEmpty(c)) {
                                    a6.a(c);
                                }
                                if (!TextUtils.isEmpty(c2)) {
                                    a6.b(c2);
                                    a6.a(new c().c(c2));
                                }
                                if (b != null) {
                                    a6.c(b.intValue());
                                }
                                if (uri != null) {
                                    a6.a(uri);
                                }
                                if (a42 != null) {
                                    a6.a(a42);
                                }
                                if (a5 != null) {
                                    a6.b(a5);
                                }
                                e = a6.e();
                            } else {
                                e = a(c, c2, identifier, b, uri, a42, a5, c(a(bundle, "gcm.n.android_channel_id")));
                            }
                            a7 = a(bundle, "gcm.n.tag");
                            notificationManager = (NotificationManager) this.b.getSystemService("notification");
                            if (TextUtils.isEmpty(a7)) {
                                a7 = "FCM-Notification:" + SystemClock.uptimeMillis();
                            }
                            notificationManager.notify(a7, 0, e);
                            return true;
                        }
                        i = a().getInt("com.google.firebase.messaging.default_notification_icon", 0);
                        if (i == 0 || !a(i)) {
                            i = this.b.getApplicationInfo().icon;
                        }
                        if (i == 0 || !a(i)) {
                            i = 17301651;
                        }
                        identifier = i;
                        b = b(a(bundle, "gcm.n.color"));
                        a8 = a(bundle, "gcm.n.sound2");
                        if (TextUtils.isEmpty(a8)) {
                            a8 = a(bundle, "gcm.n.sound");
                        }
                        if (TextUtils.isEmpty(a8)) {
                            if ("default".equals(a8)) {
                            }
                            uri = RingtoneManager.getDefaultUri(2);
                        } else {
                            uri = null;
                        }
                        a2 = a(bundle, "gcm.n.click_action");
                        if (TextUtils.isEmpty(a2)) {
                            a3 = a(bundle, "gcm.n.link_android");
                            if (TextUtils.isEmpty(a3)) {
                                a3 = a(bundle, "gcm.n.link");
                            }
                            if (TextUtils.isEmpty(a3)) {
                            }
                            if (parse != null) {
                                intent2 = this.b.getPackageManager().getLaunchIntentForPackage(this.b.getPackageName());
                            } else {
                                intent = new Intent("android.intent.action.VIEW");
                                intent.setPackage(this.b.getPackageName());
                                intent.setData(parse);
                                intent2 = intent;
                            }
                        } else {
                            intent = new Intent(a2);
                            intent.setPackage(this.b.getPackageName());
                            intent.setFlags(ErrorDialogData.BINDER_CRASH);
                            intent2 = intent;
                        }
                        if (intent2 == null) {
                            intent2.addFlags(67108864);
                            bundle2 = new Bundle(bundle);
                            FirebaseMessagingService.a(bundle2);
                            intent2.putExtras(bundle2);
                            for (String a82 : bundle2.keySet()) {
                                if (a82.startsWith("gcm.n.")) {
                                }
                                intent2.removeExtra(a82);
                            }
                            parcelable = PendingIntent.getActivity(this.b, this.f.incrementAndGet(), intent2, ErrorDialogData.SUPPRESSED);
                        } else {
                            parcelable = null;
                        }
                        if (FirebaseMessagingService.b(bundle)) {
                            a5 = null;
                            a42 = parcelable;
                        } else {
                            intent3 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                            a(intent3, bundle);
                            intent3.putExtra("pending_intent", parcelable);
                            a42 = o.a(this.b, this.f.incrementAndGet(), intent3);
                            intent = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                            a(intent, bundle);
                            a5 = o.a(this.b, this.f.incrementAndGet(), intent);
                        }
                        if (m.d()) {
                        }
                        a6 = new android.support.v4.app.NotificationCompat.d(this.b).b().a(identifier);
                        if (TextUtils.isEmpty(c)) {
                            a6.a(c);
                        }
                        if (TextUtils.isEmpty(c2)) {
                            a6.b(c2);
                            a6.a(new c().c(c2));
                        }
                        if (b != null) {
                            a6.c(b.intValue());
                        }
                        if (uri != null) {
                            a6.a(uri);
                        }
                        if (a42 != null) {
                            a6.a(a42);
                        }
                        if (a5 != null) {
                            a6.b(a5);
                        }
                        e = a6.e();
                        a7 = a(bundle, "gcm.n.tag");
                        notificationManager = (NotificationManager) this.b.getSystemService("notification");
                        if (TextUtils.isEmpty(a7)) {
                            a7 = "FCM-Notification:" + SystemClock.uptimeMillis();
                        }
                        notificationManager.notify(a7, 0, e);
                        return true;
                    }
                }
            }
        }
        i = 0;
        if (i != 0) {
            return false;
        }
        c = c(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(c)) {
            c = this.b.getApplicationInfo().loadLabel(this.b.getPackageManager());
        }
        c2 = c(bundle, "gcm.n.body");
        a82 = a(bundle, "gcm.n.icon");
        if (TextUtils.isEmpty(a82)) {
            resources = this.b.getResources();
            identifier = resources.getIdentifier(a82, "drawable", this.b.getPackageName());
            identifier = resources.getIdentifier(a82, "mipmap", this.b.getPackageName());
            new StringBuilder(String.valueOf(a82).length() + 61).append("Icon resource ").append(a82).append(" not found. Notification will use default icon.");
        }
        i = a().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        i = this.b.getApplicationInfo().icon;
        i = 17301651;
        identifier = i;
        b = b(a(bundle, "gcm.n.color"));
        a82 = a(bundle, "gcm.n.sound2");
        if (TextUtils.isEmpty(a82)) {
            a82 = a(bundle, "gcm.n.sound");
        }
        if (TextUtils.isEmpty(a82)) {
            uri = null;
        } else {
            if ("default".equals(a82)) {
            }
            uri = RingtoneManager.getDefaultUri(2);
        }
        a2 = a(bundle, "gcm.n.click_action");
        if (TextUtils.isEmpty(a2)) {
            intent = new Intent(a2);
            intent.setPackage(this.b.getPackageName());
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            intent2 = intent;
        } else {
            a3 = a(bundle, "gcm.n.link_android");
            if (TextUtils.isEmpty(a3)) {
                a3 = a(bundle, "gcm.n.link");
            }
            if (TextUtils.isEmpty(a3)) {
            }
            if (parse != null) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setPackage(this.b.getPackageName());
                intent.setData(parse);
                intent2 = intent;
            } else {
                intent2 = this.b.getPackageManager().getLaunchIntentForPackage(this.b.getPackageName());
            }
        }
        if (intent2 == null) {
            parcelable = null;
        } else {
            intent2.addFlags(67108864);
            bundle2 = new Bundle(bundle);
            FirebaseMessagingService.a(bundle2);
            intent2.putExtras(bundle2);
            for (String a822 : bundle2.keySet()) {
                if (a822.startsWith("gcm.n.")) {
                }
                intent2.removeExtra(a822);
            }
            parcelable = PendingIntent.getActivity(this.b, this.f.incrementAndGet(), intent2, ErrorDialogData.SUPPRESSED);
        }
        if (FirebaseMessagingService.b(bundle)) {
            intent3 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
            a(intent3, bundle);
            intent3.putExtra("pending_intent", parcelable);
            a42 = o.a(this.b, this.f.incrementAndGet(), intent3);
            intent = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            a(intent, bundle);
            a5 = o.a(this.b, this.f.incrementAndGet(), intent);
        } else {
            a5 = null;
            a42 = parcelable;
        }
        if (m.d()) {
        }
        a6 = new android.support.v4.app.NotificationCompat.d(this.b).b().a(identifier);
        if (TextUtils.isEmpty(c)) {
            a6.a(c);
        }
        if (TextUtils.isEmpty(c2)) {
            a6.b(c2);
            a6.a(new c().c(c2));
        }
        if (b != null) {
            a6.c(b.intValue());
        }
        if (uri != null) {
            a6.a(uri);
        }
        if (a42 != null) {
            a6.a(a42);
        }
        if (a5 != null) {
            a6.b(a5);
        }
        e = a6.e();
        a7 = a(bundle, "gcm.n.tag");
        notificationManager = (NotificationManager) this.b.getSystemService("notification");
        if (TextUtils.isEmpty(a7)) {
            a7 = "FCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(a7, 0, e);
        return true;
    }
}
