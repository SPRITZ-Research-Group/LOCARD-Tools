package com.skype.slimcore.calling;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.d;
import com.facebook.common.logging.FLog;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.CallHandler;
import com.skype.raider.R;
import com.skype.slimcore.skylib.SkyLibManager;
import com.skype.slimcore.skylib.SkyLibManager.CallHandlerExecution;

public class RNCallingService extends Service {
    public static final String a = (RNCallingService.class.getName() + ".ACTION_SHOW_ONGOING_CALL");
    public static final String b = (RNCallingService.class.getName() + ".ACTION_HIDE_ONGOING_CALL");
    public static final String c = (RNCallingService.class.getName() + ".ACTION_HANGUP_ONGOING_CALL");
    public static final String d = (RNCallingService.class.getName() + ".ACTION_MUTE_ONGOING_CALL");
    public static final String e = (RNCallingService.class.getName() + ".ACTION_UNMUTE_ONGOING_CALL");
    public static final String f = (RNCallingService.class.getName() + ".extra.thread_id");
    public static final String g = (RNCallingService.class.getName() + ".extra.call_id");
    public static final String h = (RNCallingService.class.getName() + ".extra.call_object_id");
    public static final String i = (RNCallingService.class.getName() + ".extra.message");
    public static final String j = (RNCallingService.class.getName() + ".extra.hangup");
    public static final String k = (RNCallingService.class.getName() + ".extra.show_mute");
    public static final String l = (RNCallingService.class.getName() + ".extra.mute_unmute");
    public static final String m = (RNCallingService.class.getName() + ".extra.is_muted");
    private static RNCallingNotificationProvider n;
    private boolean o = false;

    public interface RNCallingNotificationProvider {
        Class a();

        int b();
    }

    public static void a(RNCallingNotificationProvider provider) {
        n = provider;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        FLog.d("RNCallingService", "onStartCommand start ID: " + startId + " intent: " + intent);
        if (intent == null) {
            return 2;
        }
        final String action = intent.getAction();
        if (action == null) {
            return 2;
        }
        int callObjectId;
        SkyLibManager instance;
        if (action.equals(a)) {
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return 2;
            }
            boolean isMuted;
            Object threadId = bundle.getString(f, null);
            Object callId = bundle.getString(g, null);
            callObjectId = bundle.getInt(h, 0);
            CharSequence message = bundle.getString(i, null);
            String hangup = bundle.getString(j, null);
            boolean showMute = bundle.getBoolean(k, false);
            String muteAction = showMute ? bundle.getString(l, null) : null;
            if (showMute && bundle.getBoolean(m, false)) {
                isMuted = true;
            } else {
                isMuted = false;
            }
            if (n == null) {
                FLog.w("RNCallingService", "Cannot show notification - no provider");
            } else {
                Notification build;
                if (!this.o) {
                    FLog.i("RNCallingService", "Showing ongoing call notification for callId %s threadId %s callObjectId %d", callId, threadId, (Object) Integer.valueOf(callObjectId));
                    this.o = true;
                }
                Intent intent2;
                String str;
                int i;
                Intent intent3;
                if (VERSION.SDK_INT >= 26) {
                    Builder contentText = new Builder(this, "com.skype.notification").setContentTitle(getString(R.string.app_name)).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).setSmallIcon(R.drawable.notification_icon).setColor(n.b()).setContentText(message);
                    contentText.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, n.a()).setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").addFlags(ErrorDialogData.BINDER_CRASH), 134217728));
                    intent2 = new Intent(this, RNCallingService.class);
                    intent2.setAction(c);
                    intent2.putExtra(h, callObjectId);
                    contentText.addAction(R.drawable.call_btn_end, hangup, PendingIntent.getService(this, 0, intent2, ErrorDialogData.BINDER_CRASH));
                    if (showMute) {
                        str = isMuted ? e : d;
                        i = isMuted ? R.drawable.call_unmute : R.drawable.call_mute;
                        intent3 = new Intent(this, RNCallingService.class);
                        intent3.setAction(str);
                        intent3.putExtra(h, callObjectId);
                        contentText.addAction(i, muteAction, PendingIntent.getService(this, 0, intent3, ErrorDialogData.BINDER_CRASH));
                    }
                    if (VERSION.SDK_INT >= 21) {
                        contentText.setCategory("service");
                    }
                    build = contentText.build();
                } else {
                    Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    d a = new d(this, "default").a(getString(R.string.app_name));
                    a.g = decodeResource;
                    d b = a.a((int) R.drawable.notification_icon).c(n.b()).b(message);
                    b.a(PendingIntent.getActivity(this, 0, new Intent(this, n.a()).setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").addFlags(ErrorDialogData.BINDER_CRASH), 134217728));
                    intent2 = new Intent(this, RNCallingService.class);
                    intent2.setAction(c);
                    intent2.putExtra(h, callObjectId);
                    b.a(R.drawable.call_btn_end, hangup, PendingIntent.getService(this, 0, intent2, ErrorDialogData.BINDER_CRASH));
                    if (showMute) {
                        str = isMuted ? e : d;
                        i = isMuted ? R.drawable.call_unmute : R.drawable.call_mute;
                        intent3 = new Intent(this, RNCallingService.class);
                        intent3.setAction(str);
                        intent3.putExtra(h, callObjectId);
                        b.a(i, muteAction, PendingIntent.getService(this, 0, intent3, ErrorDialogData.BINDER_CRASH));
                    }
                    if (VERSION.SDK_INT >= 21) {
                        b.a("service");
                    }
                    build = b.e();
                }
                startForeground(16, build);
            }
        } else if (action.equals(b)) {
            if (this.o) {
                FLog.i("RNCallingService", "Hiding call notification");
                this.o = false;
            }
            stopForeground(true);
            stopSelf();
        } else if (action.equals(c)) {
            callObjectId = intent.getIntExtra(h, 0);
            instance = SkyLibManager.a;
            if (instance != null) {
                instance.a(new CallHandlerExecution(this) {
                    final /* synthetic */ RNCallingService b;

                    public final void a(CallHandler callHandler) {
                        callHandler.leaveCall(callObjectId);
                    }
                });
            } else {
                FLog.w("RNCallingService", "Cannot leave call - no SkyLib instance found!");
            }
        } else if (action.equals(d) || action.equals(e)) {
            callObjectId = intent.getIntExtra(h, 0);
            instance = SkyLibManager.a;
            if (instance != null) {
                instance.a(new CallHandlerExecution(this) {
                    final /* synthetic */ RNCallingService c;

                    public final void a(CallHandler callHandler) {
                        callHandler.callMute(callObjectId, action.equals(RNCallingService.d));
                    }
                });
            } else {
                FLog.w("RNCallingService", "Cannot mute call - no SkyLib instance found!");
            }
        }
        return 2;
    }

    public void onCreate() {
        FLog.d("RNCallingService", "onCreate");
        super.onCreate();
        if (VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager == null) {
                FLog.w("RNCallingService", "Invalid NotificationManager - cannot create channel");
            } else if (notificationManager.getNotificationChannel("com.skype.notification") == null) {
                NotificationChannel notificationChannel = new NotificationChannel("com.skype.notification", "Skype Notification", 0);
                notificationChannel.setLockscreenVisibility(0);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    public void onDestroy() {
        FLog.d("RNCallingService", "onDestroy");
        super.onDestroy();
    }
}
