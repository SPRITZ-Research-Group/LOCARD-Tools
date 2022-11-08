package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class di extends ct {
    private final dt a;
    private an b;
    private volatile Boolean c;
    private final ad d;
    private final ej e;
    private final List<Runnable> f = new ArrayList();
    private final ad g;

    protected di(bx bxVar) {
        super(bxVar);
        this.e = new ej(bxVar.j());
        this.a = new dt(this);
        this.d = new dj(this, bxVar);
        this.g = new dn(this, bxVar);
    }

    @WorkerThread
    private final void A() {
        c();
        this.e.a();
        this.d.a(((Long) al.H.b()).longValue());
    }

    @WorkerThread
    private final void B() {
        c();
        q().C().a("Processing queued up service tasks", Integer.valueOf(this.f.size()));
        for (Runnable run : this.f) {
            try {
                run.run();
            } catch (Exception e) {
                q().v().a("Task exception while flushing queue", e);
            }
        }
        this.f.clear();
        this.g.c();
    }

    @Nullable
    @WorkerThread
    private final zzdz a(boolean z) {
        return f().a(z ? q().E() : null);
    }

    @WorkerThread
    private final void a(Runnable runnable) throws IllegalStateException {
        c();
        if (v()) {
            runnable.run();
        } else if (((long) this.f.size()) >= 1000) {
            q().v().a("Discarding data. Max runnable queue size reached");
        } else {
            this.f.add(runnable);
            this.g.a(60000);
            x();
        }
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @WorkerThread
    @VisibleForTesting
    protected final void a(an anVar) {
        c();
        ab.a((Object) anVar);
        this.b = anVar;
        A();
        B();
    }

    @WorkerThread
    @VisibleForTesting
    final void a(an anVar, AbstractSafeParcelable abstractSafeParcelable, zzdz zzdz) {
        c();
        G();
        int i = 100;
        for (int i2 = 0; i2 < 1001 && i == 100; i2++) {
            List arrayList = new ArrayList();
            Object v = l().v();
            if (v != null) {
                arrayList.addAll(v);
                i = v.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = (ArrayList) arrayList;
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                v = arrayList2.get(i3);
                i3++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) v;
                if (abstractSafeParcelable2 instanceof zzeu) {
                    try {
                        anVar.a((zzeu) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e) {
                        q().v().a("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzjx) {
                    try {
                        anVar.a((zzjx) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e2) {
                        q().v().a("Failed to send attribute to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzed) {
                    try {
                        anVar.a((zzed) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e22) {
                        q().v().a("Failed to send conditional property to the service", e22);
                    }
                } else {
                    q().v().a("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    @WorkerThread
    protected final void a(de deVar) {
        c();
        G();
        a(new dm(this, deVar));
    }

    @WorkerThread
    protected final void a(zzed zzed) {
        ab.a((Object) zzed);
        c();
        G();
        a(new dp(this, l().a(zzed), new zzed(zzed), a(true), zzed));
    }

    @WorkerThread
    protected final void a(zzeu zzeu, String str) {
        ab.a((Object) zzeu);
        c();
        G();
        a(new do(this, l().a(zzeu), zzeu, a(true), str));
    }

    @WorkerThread
    protected final void a(zzjx zzjx) {
        c();
        G();
        a(new ds(this, l().a(zzjx), zzjx, a(true)));
    }

    @WorkerThread
    public final void a(AtomicReference<String> atomicReference) {
        c();
        G();
        a(new dk(this, atomicReference, a(false)));
    }

    @WorkerThread
    protected final void a(AtomicReference<List<zzed>> atomicReference, String str, String str2, String str3) {
        c();
        G();
        a(new dq(this, atomicReference, str, str2, str3, a(false)));
    }

    @WorkerThread
    protected final void a(AtomicReference<List<zzjx>> atomicReference, String str, String str2, String str3, boolean z) {
        c();
        G();
        a(new dr(this, atomicReference, str, str2, str3, z, a(false)));
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

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return false;
    }

    @WorkerThread
    public final boolean v() {
        c();
        G();
        return this.b != null;
    }

    @WorkerThread
    protected final void w() {
        c();
        G();
        a(new dl(this, a(true)));
    }

    @WorkerThread
    final void x() {
        Object obj = 1;
        c();
        G();
        if (!v()) {
            if (this.c == null) {
                boolean z;
                c();
                G();
                Boolean w = r().w();
                if (w == null || !w.booleanValue()) {
                    Object obj2;
                    if (f().z() != 1) {
                        q().C().a("Checking service availability");
                        int b = com.google.android.gms.common.e.b().b(n().k(), 12451);
                        int obj22;
                        switch (b) {
                            case 0:
                                q().C().a("Service available");
                                obj22 = 1;
                                z = true;
                                break;
                            case 1:
                                q().C().a("Service missing");
                                obj22 = 1;
                                z = false;
                                break;
                            case 2:
                                q().B().a("Service container out of date");
                                if (n().x() >= 12600) {
                                    w = r().w();
                                    z = w == null || w.booleanValue();
                                    obj22 = null;
                                    break;
                                }
                                obj22 = 1;
                                z = false;
                                break;
                            case 3:
                                q().y().a("Service disabled");
                                obj22 = null;
                                z = false;
                                break;
                            case 9:
                                q().y().a("Service invalid");
                                obj22 = null;
                                z = false;
                                break;
                            case 18:
                                q().y().a("Service updating");
                                obj22 = 1;
                                z = true;
                                break;
                            default:
                                q().y().a("Unexpected service status", Integer.valueOf(b));
                                obj22 = null;
                                z = false;
                                break;
                        }
                    }
                    obj22 = 1;
                    z = true;
                    if (obj22 != null) {
                        r().a(z);
                    }
                } else {
                    z = true;
                }
                this.c = Boolean.valueOf(z);
            }
            if (this.c.booleanValue()) {
                this.a.c();
                return;
            }
            List queryIntentServices = k().getPackageManager().queryIntentServices(new Intent().setClassName(k(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                obj = null;
            }
            if (obj != null) {
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                intent.setComponent(new ComponentName(k(), "com.google.android.gms.measurement.AppMeasurementService"));
                this.a.a(intent);
                return;
            }
            q().v().a("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
        }
    }

    final Boolean y() {
        return this.c;
    }

    @WorkerThread
    public final void z() {
        c();
        G();
        try {
            b.a();
            b.a(k(), this.a);
        } catch (IllegalStateException e) {
        } catch (IllegalArgumentException e2) {
        }
        this.b = null;
    }

    static /* synthetic */ void a(di diVar, ComponentName componentName) {
        diVar.c();
        if (diVar.b != null) {
            diVar.b = null;
            diVar.q().C().a("Disconnected from device MeasurementService", componentName);
            diVar.c();
            diVar.x();
        }
    }

    static /* synthetic */ void d(di diVar) {
        diVar.c();
        if (diVar.v()) {
            diVar.q().C().a("Inactivity, disconnecting from the service");
            diVar.z();
        }
    }
}
