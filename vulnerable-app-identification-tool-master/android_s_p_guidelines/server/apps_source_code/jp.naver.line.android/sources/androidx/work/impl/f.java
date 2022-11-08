package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.b;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemalarm.i;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.utils.d;
import androidx.work.p;
import defpackage.po;
import defpackage.pr;
import java.util.List;

public final class f {
    private static final String a = p.a("Schedulers");

    public static void a(b bVar, WorkDatabase workDatabase, List<e> list) {
        if (list != null && list.size() != 0) {
            pr l = workDatabase.l();
            workDatabase.g();
            try {
                List<po> a = l.a(bVar.f());
                if (a.size() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    for (po poVar : a) {
                        l.b(poVar.a, currentTimeMillis);
                    }
                }
                workDatabase.i();
                if (a.size() > 0) {
                    po[] poVarArr = (po[]) a.toArray(new po[0]);
                    for (e a2 : list) {
                        a2.a(poVarArr);
                    }
                }
            } finally {
                workDatabase.h();
            }
        }
    }

    @SuppressLint({"NewApi"})
    static e a(Context context, j jVar) {
        e bVar;
        boolean z = true;
        Throwable[] thArr;
        if (VERSION.SDK_INT >= 23) {
            bVar = new androidx.work.impl.background.systemjob.b(context, jVar);
            d.a(context, SystemJobService.class, true);
            p.a();
            thArr = new Throwable[0];
            z = false;
        } else {
            bVar = new i(context);
            p.a();
            thArr = new Throwable[0];
        }
        d.a(context, SystemAlarmService.class, z);
        return bVar;
    }
}
