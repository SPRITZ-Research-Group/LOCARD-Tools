package androidx.work.impl.utils;

import androidx.work.ad;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b;
import androidx.work.impl.e;
import androidx.work.impl.f;
import androidx.work.impl.j;
import androidx.work.u;
import androidx.work.w;
import defpackage.pr;
import java.util.UUID;

public abstract class a implements Runnable {
    private final b a = new b();

    abstract void b();

    public final u a() {
        return this.a;
    }

    public void run() {
        try {
            b();
            this.a.a(u.a);
        } catch (Throwable th) {
            this.a.a(new w(th));
        }
    }

    final void a(j jVar, String str) {
        a(jVar.d(), str);
        jVar.g().b(str);
        for (e a : jVar.f()) {
            a.a(str);
        }
    }

    static void a(j jVar) {
        f.a(jVar.e(), jVar.d(), jVar.f());
    }

    private void a(WorkDatabase workDatabase, String str) {
        pr l = workDatabase.l();
        for (String a : workDatabase.m().b(str)) {
            a(workDatabase, a);
        }
        ad f = l.f(str);
        if (f != ad.SUCCEEDED && f != ad.FAILED) {
            l.a(ad.CANCELLED, str);
        }
    }

    public static a a(final UUID uuid, final j jVar) {
        return new a() {
            final void b() {
                a(jVar, uuid.toString());
                a.a(jVar);
            }
        };
    }

    public static a a(final String str, final j jVar, final boolean z) {
        return new a() {
            final void b() {
                WorkDatabase d = jVar.d();
                d.g();
                try {
                    for (String a : d.l().h(str)) {
                        a(jVar, a);
                    }
                    d.i();
                    if (z) {
                        a.a(jVar);
                    }
                } finally {
                    d.h();
                }
            }
        };
    }
}
