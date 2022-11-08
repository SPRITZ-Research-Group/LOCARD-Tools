package com.microsoft.backgroundexecution;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public enum a {
    ;
    
    private static long f;
    public final int b;
    private final String c;
    private final Timer d;
    private Map<String, a> e;

    private class a {
        final /* synthetic */ a a;
        private WakeLock b;
        private TimerTask c;
        private double d;

        a(a aVar, WakeLock wakeLock, TimerTask timerTask) {
            this.a = aVar;
            this.b = wakeLock;
            this.c = timerTask;
            this.d = (double) System.currentTimeMillis();
        }

        static /* synthetic */ void b(a x0) {
            if (x0.c != null) {
                x0.c.cancel();
                x0.c = null;
            }
            if (x0.b != null) {
                x0.b.release();
            }
        }
    }

    private a(String str) {
        this.b = 180;
        this.c = "skype:bgexec";
        this.d = new Timer();
        this.e = new HashMap();
    }

    static {
        f = 0;
    }

    private synchronized String a() {
        long j;
        j = f + 1;
        f = j;
        return Long.toString(j);
    }

    public final synchronized String a(Context context, double maxSeconds, String debugCause) {
        String str;
        if (maxSeconds < 0.0d || maxSeconds > 180.0d) {
            str = null;
        } else {
            str = b(context, maxSeconds, debugCause);
        }
        return str;
    }

    public final synchronized String a(Context context, String debugCause) {
        return b(context, 180.0d, debugCause);
    }

    private synchronized String b(Context context, double maxSeconds, final String debugCause) {
        final String id;
        id = a();
        FLog.i("skype:bgexec", "Acquire WakeLockID=" + id + " at=" + b() + "s max_time=" + maxSeconds + "s debugCause=" + debugCause);
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            FLog.e("skype:bgexec", "Failed to get the POWER_SERVICE instance from the context!");
            id = null;
        } else {
            WakeLock wakeLock = powerManager.newWakeLock(1, "skype:bgexec:" + debugCause);
            wakeLock.setReferenceCounted(false);
            wakeLock.acquire();
            TimerTask timerTask = new TimerTask(this) {
                final /* synthetic */ a c;

                public final void run() {
                    this.c.a(id, false, debugCause);
                }
            };
            this.e.put(id, new a(this, wakeLock, timerTask));
            this.d.schedule(timerTask, (long) ((int) (1000.0d * maxSeconds)));
        }
        return id;
    }

    public final synchronized void a(String id, String debugCause) {
        a(id, true, debugCause);
    }

    private synchronized void a(String id, boolean fromPublicRequest, String debugCause) {
        Object obj;
        a wakeLockState = (a) this.e.remove(id);
        boolean isKnown = wakeLockState != null;
        String str = "skype:bgexec";
        StringBuilder append = new StringBuilder().append(fromPublicRequest ? "" : "Timeout_").append("Release WakeLockID=").append(id).append(" at=").append(b()).append("s duration=");
        if (wakeLockState == null) {
            obj = "?";
        } else {
            obj = Double.valueOf(((((double) System.currentTimeMillis()) - wakeLockState.d) / 1000.0d));
        }
        FLog.i(str, append.append(obj).append("s isKnown=").append(isKnown).append(" debugCause=").append(debugCause).toString());
        if (isKnown) {
            a.b(wakeLockState);
        }
    }

    public final synchronized void a(String debugCause) {
        FLog.i("skype:bgexec", "ReleaseAll " + this.e.size() + " WakeLocks. IDs=[" + TextUtils.join(", ", this.e.keySet()) + "] at=" + b() + "s debugCause=" + debugCause);
        for (a b : this.e.values()) {
            a.b(b);
        }
        this.d.purge();
        this.e.clear();
    }

    public final synchronized void a(Intent intent, String debugCause) {
        FLog.i("skype:bgexec", "completeWakefulIntentAfterAllWakeLocks at=" + b() + "s debugCause=" + debugCause);
        WakefulBroadcastReceiver.a(intent);
    }

    private static String b() {
        return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(((double) System.currentTimeMillis()) / 1000.0d)});
    }
}
