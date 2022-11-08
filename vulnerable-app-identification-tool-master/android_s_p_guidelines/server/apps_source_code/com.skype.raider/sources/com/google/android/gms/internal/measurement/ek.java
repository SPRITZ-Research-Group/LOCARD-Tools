package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import com.google.android.gms.common.util.e;

public final class ek extends en {
    private final AlarmManager b = ((AlarmManager) k().getSystemService("alarm"));
    private final ad c;
    private Integer d;

    protected ek(eo eoVar) {
        super(eoVar);
        this.c = new el(this, eoVar.N(), eoVar);
    }

    @TargetApi(24)
    private final void x() {
        JobScheduler jobScheduler = (JobScheduler) k().getSystemService("jobscheduler");
        q().C().a("Cancelling job. JobID", Integer.valueOf(y()));
        jobScheduler.cancel(y());
    }

    private final int y() {
        if (this.d == null) {
            String str = "measurement";
            String valueOf = String.valueOf(k().getPackageName());
            this.d = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.d.intValue();
    }

    private final PendingIntent z() {
        Intent className = new Intent().setClassName(k(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(k(), 0, className, 0);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(long j) {
        N();
        if (!bn.a(k())) {
            q().B().a("Receiver not registered/enabled");
        }
        if (!dz.a(k())) {
            q().B().a("Service not registered/enabled");
        }
        u();
        long b = j().b() + j;
        if (j < Math.max(0, ((Long) al.y.b()).longValue()) && !this.c.b()) {
            q().C().a("Scheduling upload with DelayedRunnable");
            this.c.a(j);
        }
        if (VERSION.SDK_INT >= 24) {
            q().C().a("Scheduling upload with JobScheduler");
            JobScheduler jobScheduler = (JobScheduler) k().getSystemService("jobscheduler");
            Builder builder = new Builder(y(), new ComponentName(k(), "com.google.android.gms.measurement.AppMeasurementJobService"));
            builder.setMinimumLatency(j);
            builder.setOverrideDeadline(j << 1);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            builder.setExtras(persistableBundle);
            JobInfo build = builder.build();
            q().C().a("Scheduling job. JobID", Integer.valueOf(y()));
            jobScheduler.schedule(build);
            return;
        }
        q().C().a("Scheduling upload with AlarmManager");
        this.b.setInexactRepeating(2, b, Math.max(((Long) al.t.b()).longValue(), j), z());
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ z r_() {
        return super.r_();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ t s_() {
        return super.s_();
    }

    protected final boolean t() {
        this.b.cancel(z());
        if (VERSION.SDK_INT >= 24) {
            x();
        }
        return false;
    }

    public final void u() {
        N();
        this.b.cancel(z());
        this.c.c();
        if (VERSION.SDK_INT >= 24) {
            x();
        }
    }
}
