package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import androidx.work.ad;
import androidx.work.ah;
import androidx.work.ai;
import androidx.work.b;
import androidx.work.h;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.utils.d;
import androidx.work.k;
import androidx.work.m;
import androidx.work.n;
import androidx.work.o;
import androidx.work.p;
import defpackage.bon;
import defpackage.pg;
import defpackage.po;
import defpackage.pr;
import defpackage.pu;
import defpackage.qh;
import defpackage.qi;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;

public final class l implements Runnable {
    private static final String d = p.a("WorkerWrapper");
    ListenableWorker a;
    androidx.work.l b = new m();
    bon<androidx.work.l> c = null;
    private Context e;
    private String f;
    private List<e> g;
    private ai h;
    private po i;
    private b j;
    private qi k;
    private WorkDatabase l;
    private pr m;
    private pg n;
    private pu o;
    private List<String> p;
    private String q;
    private qh<Boolean> r = qh.a();
    private volatile boolean s;

    l(m mVar) {
        this.e = mVar.a;
        this.k = mVar.c;
        this.f = mVar.f;
        this.g = mVar.g;
        this.h = mVar.h;
        this.a = mVar.b;
        this.j = mVar.d;
        this.l = mVar.e;
        this.m = this.l.l();
        this.n = this.l.m();
        this.o = this.l.n();
    }

    public final bon<Boolean> a() {
        return this.r;
    }

    public final void run() {
        this.p = this.o.a(this.f);
        List<String> list = this.p;
        StringBuilder stringBuilder = new StringBuilder("Work [ id=");
        stringBuilder.append(this.f);
        stringBuilder.append(", tags={ ");
        Object obj = 1;
        for (String str : list) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str);
        }
        stringBuilder.append(" } ]");
        this.q = stringBuilder.toString();
        if (!f()) {
            this.l.g();
            try {
                this.i = this.m.b(this.f);
                if (this.i == null) {
                    p.a().b(d, String.format("Didn't find WorkSpec for id %s", new Object[]{this.f}), new Throwable[0]);
                    a(false);
                } else if (this.i.b != ad.ENQUEUED) {
                    e();
                    this.l.i();
                    this.l.h();
                } else if ((this.i.a() || this.i.b()) && System.currentTimeMillis() < this.i.c()) {
                    a(false);
                    this.l.h();
                } else {
                    h hVar;
                    this.l.i();
                    this.l.h();
                    if (this.i.a()) {
                        hVar = this.i.e;
                    } else {
                        k a = k.a(this.i.d);
                        if (a == null) {
                            p.a().b(d, String.format("Could not create Input Merger %s", new Object[]{this.i.d}), new Throwable[0]);
                            h();
                            return;
                        }
                        List arrayList = new ArrayList();
                        arrayList.add(this.i.e);
                        arrayList.addAll(this.m.g(this.f));
                        hVar = a.a(arrayList);
                    }
                    WorkerParameters workerParameters = new WorkerParameters(UUID.fromString(this.f), hVar, this.p, this.h, this.i.k, this.j.a(), this.k, this.j.b());
                    if (this.a == null) {
                        this.a = ah.a(this.e, this.i.c, workerParameters);
                    }
                    if (this.a == null) {
                        p.a().b(d, String.format("Could not create Worker %s", new Object[]{this.i.c}), new Throwable[0]);
                        h();
                    } else if (this.a.g()) {
                        p.a().b(d, String.format("Received an already-used Worker %s; WorkerFactory should return new instances", new Object[]{this.i.c}), new Throwable[0]);
                        h();
                    } else {
                        this.a.h();
                        if (!g()) {
                            e();
                        } else if (!f()) {
                            final qh a2 = qh.a();
                            this.k.a().execute(new Runnable(this) {
                                final /* synthetic */ l b;

                                public final void run() {
                                    try {
                                        this.b.c = this.b.a.d();
                                        a2.a(this.b.c);
                                    } catch (Throwable th) {
                                        a2.a(th);
                                    }
                                }
                            });
                            final String str2 = this.q;
                            a2.a(new Runnable(this) {
                                final /* synthetic */ l c;

                                @SuppressLint({"SyntheticAccessor"})
                                public final void run() {
                                    try {
                                        androidx.work.l lVar = (androidx.work.l) a2.get();
                                        if (lVar == null) {
                                            p.a().b(l.d, String.format("%s returned a null result. Treating it as a failure.", new Object[]{this.c.i.c}), new Throwable[0]);
                                        } else {
                                            this.c.b = lVar;
                                        }
                                    } catch (CancellationException e) {
                                        p.a().a(l.d, String.format("%s was cancelled", new Object[]{str2}), e);
                                    } catch (InterruptedException e2) {
                                        p.a().b(l.d, String.format("%s failed because it threw an exception/error", new Object[]{str2}), e2);
                                    } catch (Throwable th) {
                                        this.c.b();
                                    }
                                    this.c.b();
                                }
                            }, this.k.c());
                        }
                    }
                }
            } finally {
                this.l.h();
            }
        }
    }

    public final void c() {
        this.s = true;
        f();
        if (this.c != null) {
            this.c.cancel(true);
        }
        if (this.a != null) {
            this.a.e();
        }
    }

    private void e() {
        Throwable[] thArr;
        if (this.m.f(this.f) == ad.RUNNING) {
            p.a();
            String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", new Object[]{this.f});
            thArr = new Throwable[0];
            a(true);
            return;
        }
        p.a();
        String.format("Status for %s is %s; not doing any work", new Object[]{this.f, r0});
        thArr = new Throwable[0];
        a(false);
    }

    private boolean f() {
        boolean z = false;
        if (!this.s) {
            return false;
        }
        p.a();
        String.format("Work interrupted for %s", new Object[]{this.q});
        Throwable[] thArr = new Throwable[0];
        ad f = this.m.f(this.f);
        if (!(f == null || f.a())) {
            z = true;
        }
        a(z);
        return true;
    }

    private void a(boolean z) {
        try {
            this.l.g();
            if (this.l.l().a().isEmpty()) {
                d.a(this.e, RescheduleReceiver.class, false);
            }
            this.l.i();
            this.r.a(Boolean.valueOf(z));
        } finally {
            this.l.h();
        }
    }

    private boolean g() {
        this.l.g();
        try {
            boolean z = true;
            if (this.m.f(this.f) == ad.ENQUEUED) {
                this.m.a(ad.RUNNING, this.f);
                this.m.d(this.f);
            } else {
                z = false;
            }
            this.l.i();
            return z;
        } finally {
            this.l.h();
        }
    }

    private void h() {
        this.l.g();
        try {
            a(this.f);
            this.m.a(this.f, ((m) this.b).c());
            this.l.i();
        } finally {
            this.l.h();
            a(false);
        }
    }

    private void a(String str) {
        for (String a : this.n.b(str)) {
            a(a);
        }
        if (this.m.f(str) != ad.CANCELLED) {
            this.m.a(ad.FAILED, str);
        }
    }

    private void i() {
        this.l.g();
        try {
            this.m.a(ad.ENQUEUED, this.f);
            this.m.a(this.f, System.currentTimeMillis());
            if (VERSION.SDK_INT < 23) {
                this.m.b(this.f, -1);
            }
            this.l.i();
        } finally {
            this.l.h();
            a(true);
        }
    }

    private void j() {
        this.l.g();
        try {
            this.m.a(this.f, Math.max(System.currentTimeMillis(), this.i.n + this.i.h));
            this.m.a(ad.ENQUEUED, this.f);
            this.m.e(this.f);
            if (VERSION.SDK_INT < 23) {
                this.m.b(this.f, -1);
            }
            this.l.i();
        } finally {
            this.l.h();
            a(false);
        }
    }

    private void k() {
        this.l.g();
        try {
            this.m.a(ad.SUCCEEDED, this.f);
            this.m.a(this.f, ((o) this.b).c());
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : this.n.b(this.f)) {
                if (this.m.f(str) == ad.BLOCKED && this.n.a(str)) {
                    p.a().a(d, String.format("Setting status to enqueued for %s", new Object[]{str}), new Throwable[0]);
                    this.m.a(ad.ENQUEUED, str);
                    this.m.a(str, currentTimeMillis);
                }
            }
            this.l.i();
        } finally {
            this.l.h();
            a(false);
        }
    }

    final void b() {
        if (this.k.b() == Thread.currentThread()) {
            boolean z = false;
            if (!f()) {
                try {
                    this.l.g();
                    ad f = this.m.f(this.f);
                    if (f == null) {
                        a(false);
                        z = true;
                    } else if (f == ad.RUNNING) {
                        androidx.work.l lVar = this.b;
                        if (lVar instanceof o) {
                            p.a().a(d, String.format("Worker result SUCCESS for %s", new Object[]{this.q}), new Throwable[0]);
                            if (this.i.a()) {
                                j();
                            } else {
                                k();
                            }
                        } else if (lVar instanceof n) {
                            p.a().a(d, String.format("Worker result RETRY for %s", new Object[]{this.q}), new Throwable[0]);
                            i();
                        } else {
                            p.a().a(d, String.format("Worker result FAILURE for %s", new Object[]{this.q}), new Throwable[0]);
                            if (this.i.a()) {
                                j();
                            } else {
                                h();
                            }
                        }
                        z = this.m.f(this.f).a();
                    } else if (!f.a()) {
                        i();
                    }
                    this.l.i();
                } finally {
                    z = this.l;
                    z.h();
                }
            }
            if (this.g != null) {
                if (z) {
                    for (e a : this.g) {
                        a.a(this.f);
                    }
                }
                f.a(this.j, this.l, this.g);
                return;
            }
            return;
        }
        throw new IllegalStateException("Needs to be executed on the Background executor thread.");
    }
}
