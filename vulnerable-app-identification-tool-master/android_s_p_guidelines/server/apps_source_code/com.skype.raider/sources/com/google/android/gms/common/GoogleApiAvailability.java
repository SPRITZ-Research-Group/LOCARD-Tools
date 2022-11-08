package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.d;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.a.a.b;
import com.google.android.gms.a.a.c;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.h;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.util.g;
import com.google.android.gms.common.util.m;

public class GoogleApiAvailability extends e {
    public static final int a = e.b;
    private static final Object c = new Object();
    private static final GoogleApiAvailability d = new GoogleApiAvailability();
    @GuardedBy("mLock")
    private String e;

    @SuppressLint({"HandlerLeak"})
    private class a extends Handler {
        private final Context a;
        private final /* synthetic */ GoogleApiAvailability b;

        public a(GoogleApiAvailability googleApiAvailability, Context context) {
            this.b = googleApiAvailability;
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.a = context.getApplicationContext();
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    int a = this.b.a(this.a);
                    if (this.b.a(a)) {
                        this.b.a(this.a, a);
                        return;
                    }
                    return;
                default:
                    new StringBuilder(50).append("Don't know how to handle this message: ").append(message.what);
                    return;
            }
        }
    }

    GoogleApiAvailability() {
    }

    public static Dialog a(Activity activity, OnCancelListener onCancelListener) {
        View progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        Builder builder = new Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(h.c(activity, 18));
        builder.setPositiveButton("", null);
        Dialog create = builder.create();
        a(activity, create, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return create;
    }

    private static Dialog a(Context context, int i, j jVar, OnCancelListener onCancelListener) {
        Builder builder = null;
        if (i == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843529, typedValue, true);
        if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId))) {
            builder = new Builder(context, 5);
        }
        if (builder == null) {
            builder = new Builder(context);
        }
        builder.setMessage(h.c(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        CharSequence e = h.e(context, i);
        if (e != null) {
            builder.setPositiveButton(e, jVar);
        }
        e = h.a(context, i);
        if (e != null) {
            builder.setTitle(e);
        }
        return builder.create();
    }

    public static GoogleApiAvailability a() {
        return d;
    }

    @Nullable
    public static GooglePlayServicesUpdatedReceiver a(Context context, com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver.a aVar) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        GooglePlayServicesUpdatedReceiver googlePlayServicesUpdatedReceiver = new GooglePlayServicesUpdatedReceiver(aVar);
        context.registerReceiver(googlePlayServicesUpdatedReceiver, intentFilter);
        googlePlayServicesUpdatedReceiver.a(context);
        if (k.a(context, "com.google.android.gms")) {
            return googlePlayServicesUpdatedReceiver;
        }
        aVar.a();
        googlePlayServicesUpdatedReceiver.a();
        return null;
    }

    @TargetApi(26)
    private final String a(Context context, NotificationManager notificationManager) {
        ab.a(m.d());
        String c = c();
        if (c == null) {
            c = "com.google.android.gms.availability";
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(c);
            CharSequence a = h.a(context);
            if (notificationChannel == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(c, a, 4));
            } else if (!a.equals(notificationChannel.getName())) {
                notificationChannel.setName(a);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        return c;
    }

    private static void a(Activity activity, Dialog dialog, String str, OnCancelListener onCancelListener) {
        if (activity instanceof FragmentActivity) {
            n.a(dialog, onCancelListener).a(((FragmentActivity) activity).getSupportFragmentManager(), str);
            return;
        }
        c.a(dialog, onCancelListener).show(activity.getFragmentManager(), str);
    }

    @TargetApi(20)
    private final void a(Context context, int i, PendingIntent pendingIntent) {
        if (i == 18) {
            new a(this, context).sendEmptyMessageDelayed(1, 120000);
        } else if (pendingIntent != null) {
            Notification build;
            int i2;
            CharSequence b = h.b(context, i);
            CharSequence d = h.d(context, i);
            Resources resources = context.getResources();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (g.a(context)) {
                ab.a(m.b());
                Notification.Builder style = new Notification.Builder(context).setSmallIcon(context.getApplicationInfo().icon).setPriority(2).setAutoCancel(true).setContentTitle(b).setStyle(new BigTextStyle().bigText(d));
                if (g.b(context)) {
                    style.addAction(b.common_full_open_on_phone, resources.getString(c.common_open_on_phone), pendingIntent);
                } else {
                    style.setContentIntent(pendingIntent);
                }
                if (m.d() && m.d()) {
                    style.setChannelId(a(context, notificationManager));
                }
                build = style.build();
            } else {
                d a = new d(context).a(17301642).d(resources.getString(c.common_google_play_services_notification_ticker)).a(System.currentTimeMillis()).b().a(pendingIntent).a(b).b(d).c().a(new NotificationCompat.c().c(d));
                if (m.d() && m.d()) {
                    a.c(a(context, notificationManager));
                }
                build = a.e();
            }
            switch (i) {
                case 1:
                case 2:
                case 3:
                    i2 = 10436;
                    k.e.set(false);
                    break;
                default:
                    i2 = 39789;
                    break;
            }
            notificationManager.notify(i2, build);
        }
    }

    @VisibleForTesting(otherwise = 2)
    private final String c() {
        String str;
        synchronized (c) {
            str = this.e;
        }
        return str;
    }

    public final int a(Context context) {
        return super.a(context);
    }

    @Nullable
    public final PendingIntent a(Context context, int i, int i2) {
        return super.a(context, i, i2);
    }

    @Nullable
    public final PendingIntent a(Context context, int i, int i2, @Nullable String str) {
        return super.a(context, i, i2, str);
    }

    @Nullable
    public final Intent a(Context context, int i, @Nullable String str) {
        return super.a(context, i, str);
    }

    public final boolean a(int i) {
        return super.a(i);
    }

    public final boolean a(Activity activity, int i) {
        return a(activity, i, 0, null);
    }

    public final boolean a(Activity activity, @NonNull com.google.android.gms.common.api.internal.g gVar, int i, OnCancelListener onCancelListener) {
        Dialog a = a((Context) activity, i, j.a(gVar, a((Context) activity, i, "d")), onCancelListener);
        if (a == null) {
            return false;
        }
        a(activity, a, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public final boolean a(Context context, ConnectionResult connectionResult, int i) {
        PendingIntent d = connectionResult.a() ? connectionResult.d() : a(context, connectionResult.c(), 0);
        if (d == null) {
            return false;
        }
        a(context, connectionResult.c(), GoogleApiActivity.a(context, d, i));
        return true;
    }

    public final int b(Context context) {
        return super.b(context);
    }

    public final int b(Context context, int i) {
        return super.b(context, i);
    }

    public final String b(int i) {
        return super.b(i);
    }

    public final boolean a(Activity activity, int i, int i2, OnCancelListener onCancelListener) {
        Dialog a = a((Context) activity, i, j.a(activity, a((Context) activity, i, "d"), i2), onCancelListener);
        if (a == null) {
            return false;
        }
        a(activity, a, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public final void a(Context context, int i) {
        a(context, i, a(context, i, 0, "n"));
    }
}
