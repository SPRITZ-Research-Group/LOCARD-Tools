package androidx.work.impl.utils;

import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.work.ad;
import androidx.work.af;
import androidx.work.d;
import androidx.work.i;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.f;
import androidx.work.impl.g;
import androidx.work.impl.j;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import androidx.work.p;
import androidx.work.u;
import androidx.work.w;
import defpackage.pf;
import defpackage.pg;
import defpackage.pl;
import defpackage.po;
import defpackage.pp;
import defpackage.pr;
import defpackage.pt;
import java.util.ArrayList;
import java.util.List;

public final class b implements Runnable {
    private static final String a = p.a("EnqueueRunnable");
    private final g b;
    private final androidx.work.impl.b c = new androidx.work.impl.b();

    public b(g gVar) {
        this.b = gVar;
    }

    public final void run() {
        try {
            if (this.b.j()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", new Object[]{this.b}));
            }
            if (b()) {
                d.a(this.b.a().c(), RescheduleReceiver.class, true);
                j a = this.b.a();
                f.a(a.e(), a.d(), a.f());
            }
            this.c.a(u.a);
        } catch (Throwable th) {
            this.c.a(new w(th));
        }
    }

    public final u a() {
        return this.c;
    }

    private boolean b() {
        WorkDatabase d = this.b.a().d();
        d.g();
        try {
            boolean a = a(this.b);
            d.i();
            return a;
        } finally {
            d.h();
        }
    }

    private static boolean a(g gVar) {
        List<g> h = gVar.h();
        int i = 0;
        if (h != null) {
            int i2 = 0;
            for (g gVar2 : h) {
                if (gVar2.f()) {
                    Throwable[] thArr = new Throwable[0];
                    p.a().a(a, String.format("Already enqueued work ids (%s).", new Object[]{TextUtils.join(", ", gVar2.e())}));
                } else {
                    i2 |= a(gVar2);
                }
            }
            i = i2;
        }
        return b(gVar) | i;
    }

    private static boolean b(g gVar) {
        boolean a = a(gVar.a(), gVar.d(), (String[]) g.a(gVar).toArray(new String[0]), gVar.b(), gVar.c());
        gVar.g();
        return a;
    }

    private static boolean a(j jVar, List<? extends af> list, String[] strArr, String str, androidx.work.j jVar2) {
        int i;
        Object obj;
        Object obj2;
        boolean z;
        po b;
        int length;
        int i2;
        Object[] objArr;
        boolean z2;
        Object[] objArr2 = strArr;
        String str2 = str;
        androidx.work.j jVar3 = jVar2;
        long currentTimeMillis = System.currentTimeMillis();
        WorkDatabase d = jVar.d();
        Object obj3 = (objArr2 == null || objArr2.length <= 0) ? null : 1;
        if (obj3 != null) {
            i = 1;
            obj = null;
            obj2 = null;
            for (String b2 : objArr2) {
                po b3 = d.l().b(b2);
                if (b3 == null) {
                    p.a().b(a, String.format("Prerequisite %s doesn't exist; not enqueuing", new Object[]{b2}), new Throwable[0]);
                    return false;
                }
                ad adVar = b3.b;
                i &= adVar == ad.SUCCEEDED ? 1 : 0;
                if (adVar == ad.FAILED) {
                    obj = 1;
                } else if (adVar == ad.CANCELLED) {
                    obj2 = 1;
                }
            }
        } else {
            i = 1;
            obj = null;
            obj2 = null;
        }
        int isEmpty = TextUtils.isEmpty(str) ^ 1;
        Object obj4 = (isEmpty == 0 || obj3 != null) ? null : 1;
        if (obj4 != null) {
            List<pp> c = d.l().c(str2);
            if (!c.isEmpty()) {
                if (jVar3 == androidx.work.j.APPEND) {
                    pg m = d.m();
                    List arrayList = new ArrayList();
                    for (pp ppVar : c) {
                        if (!m.c(ppVar.a)) {
                            int i3 = (ppVar.b == ad.SUCCEEDED ? 1 : 0) & i;
                            if (ppVar.b == ad.FAILED) {
                                obj = 1;
                            } else if (ppVar.b == ad.CANCELLED) {
                                obj2 = 1;
                            }
                            arrayList.add(ppVar.a);
                            i = i3;
                        }
                    }
                    objArr2 = (String[]) arrayList.toArray(objArr2);
                    obj3 = objArr2.length > 0 ? 1 : null;
                } else {
                    if (jVar3 == androidx.work.j.KEEP) {
                        for (pp ppVar2 : c) {
                            if (ppVar2.b != ad.ENQUEUED) {
                                if (ppVar2.b == ad.RUNNING) {
                                }
                            }
                            return false;
                        }
                    }
                    a.a(str2, jVar, false).run();
                    pr l = d.l();
                    for (pp ppVar3 : c) {
                        l.a(ppVar3.a);
                    }
                    z = true;
                    for (af afVar : list) {
                        b = afVar.b();
                        if (obj3 != null || r11 != 0) {
                            b.n = currentTimeMillis;
                        } else if (obj != null) {
                            b.b = ad.FAILED;
                        } else if (obj2 != null) {
                            b.b = ad.CANCELLED;
                        } else {
                            b.b = ad.BLOCKED;
                        }
                        if (VERSION.SDK_INT >= 23 && VERSION.SDK_INT <= 25) {
                            a(b);
                        }
                        if (b.b == ad.ENQUEUED) {
                            z = true;
                        }
                        d.l().a(b);
                        if (obj3 != null) {
                            length = objArr2.length;
                            i2 = 0;
                            while (i2 < length) {
                                objArr = objArr2;
                                z2 = z;
                                d.m().a(new pf(afVar.a(), objArr2[i2]));
                                i2++;
                                objArr2 = objArr;
                                z = z2;
                            }
                        }
                        objArr = objArr2;
                        z2 = z;
                        for (String ptVar : afVar.c()) {
                            d.n().a(new pt(ptVar, afVar.a()));
                        }
                        if (isEmpty != 0) {
                            d.p().a(new pl(str2, afVar.a()));
                        }
                        objArr2 = objArr;
                        z = z2;
                    }
                    return z;
                }
            }
        }
        z = false;
        for (af afVar2 : list) {
            b = afVar2.b();
            if (obj3 != null) {
            }
            b.n = currentTimeMillis;
            a(b);
            if (b.b == ad.ENQUEUED) {
                z = true;
            }
            d.l().a(b);
            if (obj3 != null) {
                length = objArr2.length;
                i2 = 0;
                while (i2 < length) {
                    objArr = objArr2;
                    z2 = z;
                    d.m().a(new pf(afVar2.a(), objArr2[i2]));
                    i2++;
                    objArr2 = objArr;
                    z = z2;
                }
            }
            objArr = objArr2;
            z2 = z;
            while (r0.hasNext()) {
                d.n().a(new pt(ptVar, afVar2.a()));
            }
            if (isEmpty != 0) {
                d.p().a(new pl(str2, afVar2.a()));
            }
            objArr2 = objArr;
            z = z2;
        }
        return z;
    }

    private static void a(po poVar) {
        d dVar = poVar.j;
        if (dVar.d() || dVar.e()) {
            String str = poVar.c;
            i iVar = new i();
            iVar.a(poVar.e).a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", str);
            poVar.c = ConstraintTrackingWorker.class.getName();
            poVar.e = iVar.a();
        }
    }
}
