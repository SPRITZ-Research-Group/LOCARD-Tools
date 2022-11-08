package androidx.work.impl.utils;

import androidx.work.ad;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.j;
import androidx.work.p;
import defpackage.pr;

public final class g implements Runnable {
    private static final String a = p.a("StopWorkRunnable");
    private j b;
    private String c;

    public g(j jVar, String str) {
        this.b = jVar;
        this.c = str;
    }

    public final void run() {
        WorkDatabase d = this.b.d();
        pr l = d.l();
        d.g();
        try {
            if (l.f(this.c) == ad.RUNNING) {
                l.a(ad.ENQUEUED, this.c);
            }
            boolean a = this.b.g().a(this.c);
            p.a();
            String.format("StopWorkRunnable for %s; Processor.stopWork = %s", new Object[]{this.c, Boolean.valueOf(a)});
            Throwable[] thArr = new Throwable[0];
            d.i();
        } finally {
            d.h();
        }
    }
}
