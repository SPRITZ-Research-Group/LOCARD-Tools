package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.impl.j;
import androidx.work.impl.utils.c;
import androidx.work.p;
import defpackage.pi;
import defpackage.pj;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

final class a {
    private static final String a = p.a("Alarms");

    public static void a(Context context, j jVar, String str, long j) {
        pj o = jVar.d().o();
        pi a = o.a(str);
        if (a != null) {
            a(context, str, a.b);
            a(context, str, a.b, j);
            return;
        }
        int a2 = new c(context).a();
        o.a(new pi(str, a2));
        a(context, str, a2, j);
    }

    public static void a(Context context, j jVar, String str) {
        pj o = jVar.d().o();
        pi a = o.a(str);
        if (a != null) {
            a(context, str, a.b);
            p.a();
            String.format("Removing SystemIdInfo for workSpecId (%s)", new Object[]{str});
            Throwable[] thArr = new Throwable[0];
            o.b(str);
        }
    }

    private static void a(Context context, String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), PKIFailureInfo.duplicateCertReq);
        if (service != null && alarmManager != null) {
            p.a();
            String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", new Object[]{str, Integer.valueOf(i)});
            Throwable[] thArr = new Throwable[0];
            alarmManager.cancel(service);
        }
    }

    private static void a(Context context, String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), 1073741824);
        if (alarmManager != null) {
            if (VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, j, service);
                return;
            }
            alarmManager.set(0, j, service);
        }
    }
}
