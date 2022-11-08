package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ab;

final class r {
    private long A;
    private long B;
    private final bx a;
    private final String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;
    private long h;
    private long i;
    private String j;
    private long k;
    private String l;
    private long m;
    private long n;
    private boolean o;
    private long p;
    private boolean q;
    private boolean r;
    private long s;
    private long t;
    private long u;
    private long v;
    private long w;
    private long x;
    private String y;
    private boolean z;

    @WorkerThread
    r(bx bxVar, String str) {
        ab.a((Object) bxVar);
        ab.a(str);
        this.a = bxVar;
        this.b = str;
        this.a.x();
    }

    @WorkerThread
    public final long A() {
        this.a.x();
        return this.p;
    }

    @WorkerThread
    public final boolean B() {
        this.a.x();
        return this.q;
    }

    @WorkerThread
    public final boolean C() {
        this.a.x();
        return this.r;
    }

    @WorkerThread
    public final void a() {
        this.a.x();
        this.z = false;
    }

    @WorkerThread
    public final void a(long j) {
        this.a.x();
        this.z = (this.h != j ? 1 : 0) | this.z;
        this.h = j;
    }

    @WorkerThread
    public final void a(String str) {
        this.a.x();
        this.z = (!ew.b(this.c, str) ? 1 : 0) | this.z;
        this.c = str;
    }

    @WorkerThread
    public final void a(boolean z) {
        this.a.x();
        this.z = (this.o != z ? 1 : 0) | this.z;
        this.o = z;
    }

    @WorkerThread
    public final String b() {
        this.a.x();
        return this.b;
    }

    @WorkerThread
    public final void b(long j) {
        this.a.x();
        this.z = (this.i != j ? 1 : 0) | this.z;
        this.i = j;
    }

    @WorkerThread
    public final void b(String str) {
        this.a.x();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.z = (!ew.b(this.d, str) ? 1 : 0) | this.z;
        this.d = str;
    }

    @WorkerThread
    public final void b(boolean z) {
        this.a.x();
        this.z = this.q != z;
        this.q = z;
    }

    @WorkerThread
    public final String c() {
        this.a.x();
        return this.c;
    }

    @WorkerThread
    public final void c(long j) {
        this.a.x();
        this.z = (this.k != j ? 1 : 0) | this.z;
        this.k = j;
    }

    @WorkerThread
    public final void c(String str) {
        this.a.x();
        this.z = (!ew.b(this.e, str) ? 1 : 0) | this.z;
        this.e = str;
    }

    @WorkerThread
    public final void c(boolean z) {
        this.a.x();
        this.z = this.r != z;
        this.r = z;
    }

    @WorkerThread
    public final String d() {
        this.a.x();
        return this.d;
    }

    @WorkerThread
    public final void d(long j) {
        this.a.x();
        this.z = (this.m != j ? 1 : 0) | this.z;
        this.m = j;
    }

    @WorkerThread
    public final void d(String str) {
        this.a.x();
        this.z = (!ew.b(this.f, str) ? 1 : 0) | this.z;
        this.f = str;
    }

    @WorkerThread
    public final String e() {
        this.a.x();
        return this.e;
    }

    @WorkerThread
    public final void e(long j) {
        this.a.x();
        this.z = (this.n != j ? 1 : 0) | this.z;
        this.n = j;
    }

    @WorkerThread
    public final void e(String str) {
        this.a.x();
        this.z = (!ew.b(this.j, str) ? 1 : 0) | this.z;
        this.j = str;
    }

    @WorkerThread
    public final String f() {
        this.a.x();
        return this.f;
    }

    @WorkerThread
    public final void f(long j) {
        int i = 1;
        ab.b(j >= 0);
        this.a.x();
        boolean z = this.z;
        if (this.g == j) {
            i = 0;
        }
        this.z = z | i;
        this.g = j;
    }

    @WorkerThread
    public final void f(String str) {
        this.a.x();
        this.z = (!ew.b(this.l, str) ? 1 : 0) | this.z;
        this.l = str;
    }

    @WorkerThread
    public final long g() {
        this.a.x();
        return this.h;
    }

    @WorkerThread
    public final void g(long j) {
        this.a.x();
        this.z = (this.A != j ? 1 : 0) | this.z;
        this.A = j;
    }

    @WorkerThread
    public final void g(String str) {
        this.a.x();
        this.z = (!ew.b(this.y, str) ? 1 : 0) | this.z;
        this.y = str;
    }

    @WorkerThread
    public final long h() {
        this.a.x();
        return this.i;
    }

    @WorkerThread
    public final void h(long j) {
        this.a.x();
        this.z = (this.B != j ? 1 : 0) | this.z;
        this.B = j;
    }

    @WorkerThread
    public final String i() {
        this.a.x();
        return this.j;
    }

    @WorkerThread
    public final void i(long j) {
        this.a.x();
        this.z = (this.s != j ? 1 : 0) | this.z;
        this.s = j;
    }

    @WorkerThread
    public final long j() {
        this.a.x();
        return this.k;
    }

    @WorkerThread
    public final void j(long j) {
        this.a.x();
        this.z = (this.t != j ? 1 : 0) | this.z;
        this.t = j;
    }

    @WorkerThread
    public final String k() {
        this.a.x();
        return this.l;
    }

    @WorkerThread
    public final void k(long j) {
        this.a.x();
        this.z = (this.u != j ? 1 : 0) | this.z;
        this.u = j;
    }

    @WorkerThread
    public final long l() {
        this.a.x();
        return this.m;
    }

    @WorkerThread
    public final void l(long j) {
        this.a.x();
        this.z = (this.v != j ? 1 : 0) | this.z;
        this.v = j;
    }

    @WorkerThread
    public final long m() {
        this.a.x();
        return this.n;
    }

    @WorkerThread
    public final void m(long j) {
        this.a.x();
        this.z = (this.x != j ? 1 : 0) | this.z;
        this.x = j;
    }

    @WorkerThread
    public final void n(long j) {
        this.a.x();
        this.z = (this.w != j ? 1 : 0) | this.z;
        this.w = j;
    }

    @WorkerThread
    public final boolean n() {
        this.a.x();
        return this.o;
    }

    @WorkerThread
    public final long o() {
        this.a.x();
        return this.g;
    }

    @WorkerThread
    public final void o(long j) {
        this.a.x();
        this.z = (this.p != j ? 1 : 0) | this.z;
        this.p = j;
    }

    @WorkerThread
    public final long p() {
        this.a.x();
        return this.A;
    }

    @WorkerThread
    public final long q() {
        this.a.x();
        return this.B;
    }

    @WorkerThread
    public final void r() {
        this.a.x();
        long j = this.g + 1;
        if (j > 2147483647L) {
            this.a.q().y().a("Bundle index overflow. appId", av.a(this.b));
            j = 0;
        }
        this.z = true;
        this.g = j;
    }

    @WorkerThread
    public final long s() {
        this.a.x();
        return this.s;
    }

    @WorkerThread
    public final long t() {
        this.a.x();
        return this.t;
    }

    @WorkerThread
    public final long u() {
        this.a.x();
        return this.u;
    }

    @WorkerThread
    public final long v() {
        this.a.x();
        return this.v;
    }

    @WorkerThread
    public final long w() {
        this.a.x();
        return this.x;
    }

    @WorkerThread
    public final long x() {
        this.a.x();
        return this.w;
    }

    @WorkerThread
    public final String y() {
        this.a.x();
        return this.y;
    }

    @WorkerThread
    public final String z() {
        this.a.x();
        String str = this.y;
        g(null);
        return str;
    }
}
