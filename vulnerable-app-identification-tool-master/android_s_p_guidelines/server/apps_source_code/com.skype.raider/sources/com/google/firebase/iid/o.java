package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.util.l;
import android.util.Log;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

public final class o {
    private static o b;
    @VisibleForTesting
    final Queue<Intent> a = new ArrayDeque();
    @GuardedBy("serviceClassNames")
    private final l<String, String> c = new l();
    private Boolean d = null;
    @VisibleForTesting
    private final Queue<Intent> e = new ArrayDeque();

    private o() {
    }

    private final int a(Context context, Intent intent) {
        String str;
        String str2;
        synchronized (this.c) {
            str = (String) this.c.get(intent.getAction());
        }
        if (str == null) {
            ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (!(resolveService == null || resolveService.serviceInfo == null)) {
                ServiceInfo serviceInfo = resolveService.serviceInfo;
                if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                    str2 = serviceInfo.packageName;
                    str = serviceInfo.name;
                    new StringBuilder((String.valueOf(str2).length() + 94) + String.valueOf(str).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(str2).append("/").append(str);
                } else {
                    str = serviceInfo.name;
                    if (str.startsWith(".")) {
                        str2 = String.valueOf(context.getPackageName());
                        str = String.valueOf(str);
                        str = str.length() != 0 ? str2.concat(str) : new String(str2);
                    }
                    synchronized (this.c) {
                        this.c.put(intent.getAction(), str);
                    }
                }
            }
            if (this.d == null) {
                this.d = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0);
            }
            return (this.d.booleanValue() ? WakefulBroadcastReceiver.a_(context, intent) : context.startService(intent)) != null ? 404 : -1;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            str2 = "Restricting intent to a specific service: ";
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str2.concat(valueOf);
            } else {
                valueOf = new String(str2);
            }
        }
        intent.setClassName(context.getPackageName(), str);
        try {
            if (this.d == null) {
                if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0) {
                }
                this.d = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0);
            }
            if (this.d.booleanValue()) {
            }
            if ((this.d.booleanValue() ? WakefulBroadcastReceiver.a_(context, intent) : context.startService(intent)) != null) {
            }
        } catch (SecurityException e) {
            return 401;
        } catch (IllegalStateException e2) {
            str = String.valueOf(e2);
            new StringBuilder(String.valueOf(str).length() + 45).append("Failed to start service while in background: ").append(str);
            return 402;
        }
    }

    public static PendingIntent a(Context context, int i, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction("com.google.firebase.MESSAGING_EVENT");
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, ErrorDialogData.SUPPRESSED);
    }

    public static synchronized o a() {
        o oVar;
        synchronized (o.class) {
            if (b == null) {
                b = new o();
            }
            oVar = b;
        }
        return oVar;
    }

    public final int a(Context context, String str, Intent intent) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    obj = null;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.a.offer(intent);
                break;
            case 1:
                this.e.offer(intent);
                break;
            default:
                String str2 = "Unknown service action: ";
                String valueOf = String.valueOf(str);
                if (valueOf.length() != 0) {
                    str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
                return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return a(context, intent2);
    }

    public final Intent b() {
        return (Intent) this.e.poll();
    }
}
