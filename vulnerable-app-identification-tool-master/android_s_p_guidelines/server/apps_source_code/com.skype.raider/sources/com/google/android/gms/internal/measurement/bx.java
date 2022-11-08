package com.google.android.gms.internal.measurement;

import android.app.Application;
import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.e;
import com.google.android.gms.common.util.f;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.atomic.AtomicReference;

public class bx extends eo implements cu {
    private static volatile bx d;
    private int A;
    private int B;
    private final long C;
    private final Context e;
    private final w f;
    private final bf g;
    private final av h;
    private final bs i;
    private final ee j;
    private final AppMeasurement k;
    private final FirebaseAnalytics l;
    private final ew m;
    private final at n;
    private final e o;
    private final df p;
    private final cw q;
    private final n r;
    private ar s;
    private di t;
    private af u;
    private aq v;
    private bk w;
    private boolean x = false;
    private Boolean y;
    private long z;

    private bx(cv cvVar) {
        ab.a((Object) cvVar);
        a(this);
        this.e = cvVar.a;
        ga.a(this.e);
        this.c = -1;
        this.o = f.d();
        this.C = this.o.a();
        this.f = new w(this);
        ct bfVar = new bf(this);
        bfVar.H();
        this.g = bfVar;
        bfVar = new av(this);
        bfVar.H();
        this.h = bfVar;
        bfVar = new ew(this);
        bfVar.H();
        this.m = bfVar;
        bfVar = new at(this);
        bfVar.H();
        this.n = bfVar;
        this.r = new n(this);
        bfVar = new df(this);
        bfVar.H();
        this.p = bfVar;
        bfVar = new cw(this);
        bfVar.H();
        this.q = bfVar;
        this.k = new AppMeasurement(this);
        this.l = new FirebaseAnalytics(this);
        bfVar = new ee(this);
        bfVar.H();
        this.j = bfVar;
        bfVar = new bs(this);
        bfVar.H();
        this.i = bfVar;
        if (this.e.getApplicationContext() instanceof Application) {
            cs i = i();
            if (i.k().getApplicationContext() instanceof Application) {
                Application application = (Application) i.k().getApplicationContext();
                if (i.a == null) {
                    i.a = new dd(i, (byte) 0);
                }
                application.unregisterActivityLifecycleCallbacks(i.a);
                application.registerActivityLifecycleCallbacks(i.a);
                i.q().C().a("Registered activity lifecycle callback");
            }
        } else {
            q().y().a("Application context is not an Application");
        }
        en azVar = new az(this);
        azVar.O();
        this.b = azVar;
        azVar = new br(this);
        azVar.O();
        this.a = azVar;
        this.i.a(new by(this, cvVar));
    }

    static void A() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private final void P() {
        if (!this.x) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    public static bx a(Context context) {
        ab.a((Object) context);
        ab.a(context.getApplicationContext());
        if (d == null) {
            synchronized (bx.class) {
                if (d == null) {
                    d = new bx(new cv(context));
                }
            }
        }
        return d;
    }

    private static void a(cs csVar) {
        if (csVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void a(ct ctVar) {
        if (ctVar == null) {
            throw new IllegalStateException("Component not created");
        } else if (!ctVar.F()) {
            String valueOf = String.valueOf(ctVar.getClass());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Component not initialized: ").append(valueOf).toString());
        }
    }

    final void B() {
        this.A++;
    }

    final void C() {
        this.B++;
    }

    @WorkerThread
    protected final boolean D() {
        P();
        x();
        if (this.y == null || this.z == 0 || !(this.y == null || this.y.booleanValue() || Math.abs(j().b() - this.z) <= 1000)) {
            this.z = j().b();
            boolean z = n().f("android.permission.INTERNET") && n().f("android.permission.ACCESS_NETWORK_STATE") && (c.a(k()).a() || (bn.a(k()) && dz.a(k())));
            this.y = Boolean.valueOf(z);
            if (this.y.booleanValue()) {
                this.y = Boolean.valueOf(n().d(v().x()));
            }
        }
        return this.y.booleanValue();
    }

    @WorkerThread
    final void a() {
        ax A;
        x();
        ct afVar = new af(this);
        afVar.H();
        this.u = afVar;
        afVar = new aq(this);
        afVar.H();
        this.v = afVar;
        ct arVar = new ar(this);
        arVar.H();
        this.s = arVar;
        arVar = new di(this);
        arVar.H();
        this.t = arVar;
        this.m.I();
        this.g.I();
        this.w = new bk(this);
        this.v.I();
        q().A().a("App measurement is starting up, version", Long.valueOf(12451));
        q().A().a("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String w = afVar.w();
        if (n().h(w)) {
            A = q().A();
            w = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            A = q().A();
            String str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
            w = String.valueOf(w);
            w = w.length() != 0 ? str.concat(w) : new String(str);
        }
        A.a(w);
        q().B().a("Debug-level message logging enabled");
        if (this.A != this.B) {
            q().v().a("Not all components initialized", Integer.valueOf(this.A), Integer.valueOf(this.B));
        }
        super.E();
        this.x = true;
    }

    @WorkerThread
    protected final void b() {
        x();
        if (d().c.a() == 0) {
            d().c.a(j().a());
        }
        if (Long.valueOf(d().h.a()).longValue() == 0) {
            q().C().a("Persisting first open", Long.valueOf(this.C));
            d().h.a(this.C);
        }
        if (D()) {
            if (!TextUtils.isEmpty(v().x())) {
                String v = d().v();
                if (v == null) {
                    d().c(v().x());
                } else if (!v.equals(v().x())) {
                    q().A().a("Rechecking which service to use due to a GMP App Id change");
                    d().x();
                    this.t.z();
                    this.t.x();
                    d().c(v().x());
                    d().h.a(this.C);
                    d().j.a(null);
                }
            }
            i().a(d().j.a());
            if (!TextUtils.isEmpty(v().x())) {
                boolean y = y();
                if (!(d().A() || c().u())) {
                    d().c(!y);
                }
                if (!c().i(v().w()) || y) {
                    i().w();
                }
                t().a(new AtomicReference());
            }
        } else if (y()) {
            if (!n().f("android.permission.INTERNET")) {
                q().v().a("App is missing INTERNET permission");
            }
            if (!n().f("android.permission.ACCESS_NETWORK_STATE")) {
                q().v().a("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!c.a(k()).a()) {
                if (!bn.a(k())) {
                    q().v().a("AppMeasurementReceiver not registered/enabled");
                }
                if (!dz.a(k())) {
                    q().v().a("AppMeasurementService not registered/enabled");
                }
            }
            q().v().a("Uploading is not possible. App measurement disabled");
        }
        super.b();
    }

    public final w c() {
        return this.f;
    }

    public final bf d() {
        a(this.g);
        return this.g;
    }

    public final av e() {
        return (this.h == null || !this.h.F()) ? null : this.h;
    }

    public final ee f() {
        a(this.j);
        return this.j;
    }

    public final bk g() {
        return this.w;
    }

    final bs h() {
        return this.i;
    }

    public final cw i() {
        a(this.q);
        return this.q;
    }

    public final e j() {
        return this.o;
    }

    public final Context k() {
        return this.e;
    }

    public final AppMeasurement l() {
        return this.k;
    }

    public final FirebaseAnalytics m() {
        return this.l;
    }

    public final ew n() {
        a(this.m);
        return this.m;
    }

    public final at o() {
        a(this.n);
        return this.n;
    }

    public final bs p() {
        a(this.i);
        return this.i;
    }

    public final av q() {
        a(this.h);
        return this.h;
    }

    public final ar r() {
        a(this.s);
        return this.s;
    }

    public final df s() {
        a(this.p);
        return this.p;
    }

    public final di t() {
        a(this.t);
        return this.t;
    }

    public final af u() {
        a(this.u);
        return this.u;
    }

    public final aq v() {
        a(this.v);
        return this.v;
    }

    public final n w() {
        a(this.r);
        return this.r;
    }

    @WorkerThread
    public final void x() {
        p().c();
    }

    @WorkerThread
    public final boolean y() {
        boolean z = false;
        x();
        P();
        if (c().u()) {
            return false;
        }
        Boolean b = c().b("firebase_analytics_collection_enabled");
        if (b != null) {
            z = b.booleanValue();
        } else if (!com.google.android.gms.common.api.internal.e.b()) {
            z = true;
        }
        return d().b(z);
    }

    final long z() {
        Long valueOf = Long.valueOf(d().h.a());
        return valueOf.longValue() == 0 ? this.C : Math.min(this.C, valueOf.longValue());
    }
}
