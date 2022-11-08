package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

public class g {
    private final q a;
    private final long b;
    private final c c;
    private final aw d;
    private final AppLovinSdkImpl e;
    private final Object f = new Object();
    private long g;
    private long h;
    private long i;

    public g(AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (appLovinSdk != null) {
            long l;
            AppLovinSdkImpl appLovinSdkImpl = (AppLovinSdkImpl) appLovinSdk;
            this.c = appLovinSdkImpl.b();
            this.d = appLovinSdkImpl.a();
            this.e = appLovinSdkImpl;
            if (appLovinAd instanceof q) {
                this.a = (q) appLovinAd;
                l = this.a.l();
            } else {
                this.a = null;
                l = 0;
            }
            this.b = l;
        } else {
            throw new IllegalArgumentException("No sdk specified");
        }
    }

    static void a(long j, q qVar, AppLovinSdkImpl appLovinSdkImpl) {
        if (qVar != null && appLovinSdkImpl != null) {
            appLovinSdkImpl.b().a(b.a, j, qVar);
        }
    }

    private void a(b bVar) {
        synchronized (this.f) {
            if (this.g > 0) {
                this.c.a(bVar, System.currentTimeMillis() - this.g, this.a);
            }
        }
    }

    static void a(q qVar, AppLovinSdkImpl appLovinSdkImpl) {
        if (qVar != null && appLovinSdkImpl != null) {
            appLovinSdkImpl.b().a(b.b, qVar.r(), qVar);
            appLovinSdkImpl.b().a(b.c, qVar.s(), qVar);
        }
    }

    static void a(z zVar, q qVar, AppLovinSdkImpl appLovinSdkImpl) {
        if (qVar != null && appLovinSdkImpl != null && zVar != null) {
            appLovinSdkImpl.b().a(b.d, zVar.a(), qVar);
            appLovinSdkImpl.b().a(b.e, zVar.b(), qVar);
        }
    }

    public void a() {
        this.c.a(b.i, this.d.a("ad_imp"), this.a);
        this.c.a(b.h, this.d.a("ad_imp_session"), this.a);
        synchronized (this.f) {
            long j = 0;
            if (this.b > 0) {
                this.g = System.currentTimeMillis();
                this.c.a(b.g, this.g - this.e.getInitializedTimeMillis(), this.a);
                this.c.a(b.f, this.g - this.b, this.a);
                if (ag.a(this.e.getApplicationContext(), this.e)) {
                    j = 1;
                }
                this.c.a(b.o, j, this.a);
            }
        }
    }

    public void a(long j) {
        this.c.a(b.p, j, this.a);
    }

    public void b() {
        synchronized (this.f) {
            if (this.h < 1) {
                this.h = System.currentTimeMillis();
                if (this.g > 0) {
                    this.c.a(b.l, this.h - this.g, this.a);
                }
            }
        }
    }

    public void b(long j) {
        this.c.a(b.q, j, this.a);
    }

    public void c() {
        a(b.j);
    }

    public void c(long j) {
        synchronized (this.f) {
            if (this.i < 1) {
                this.i = j;
                this.c.a(b.r, j, this.a);
            }
        }
    }

    public void d() {
        a(b.m);
    }

    public void e() {
        a(b.n);
    }

    public void f() {
        a(b.k);
    }
}
