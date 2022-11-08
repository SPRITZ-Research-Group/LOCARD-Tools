package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.work.impl.j;
import androidx.work.p;
import com.google.android.exoplayer.C;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

public final class ForceStopRunnable implements Runnable {
    private static final String a = p.a("ForceStopRunnable");
    private static final long b = TimeUnit.DAYS.toMillis(3650);
    private final Context c;
    private final j d;

    public class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String a = p.a("ForceStopRunnable$Rcvr");

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                    p.a();
                    Throwable[] thArr = new Throwable[0];
                    ForceStopRunnable.a(context);
                }
            }
        }
    }

    public ForceStopRunnable(Context context, j jVar) {
        this.c = context.getApplicationContext();
        this.d = jVar;
    }

    private static PendingIntent a(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, b(context), i);
    }

    private static Intent b(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    static void a(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent a = a(context, C.SAMPLE_FLAG_DECODE_ONLY);
        long currentTimeMillis = System.currentTimeMillis() + b;
        if (alarmManager != null) {
            if (VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, currentTimeMillis, a);
                return;
            }
            alarmManager.set(0, currentTimeMillis, a);
        }
    }

    public final void run() {
        Throwable[] thArr;
        if (this.d.i().a()) {
            p.a();
            thArr = new Throwable[0];
            this.d.j();
            this.d.i().a(false);
        } else {
            Object obj;
            if (a(this.c, PKIFailureInfo.duplicateCertReq) == null) {
                a(this.c);
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                p.a();
                thArr = new Throwable[0];
                this.d.j();
            }
        }
        this.d.k();
    }
}
