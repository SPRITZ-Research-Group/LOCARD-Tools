package androidx.work;

import android.os.Build.VERSION;

public final class d {
    public static final d a = new e().d();
    private r b = r.NOT_REQUIRED;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private long g = -1;
    private long h = -1;
    private f i = new f();

    d(e eVar) {
        this.c = eVar.a;
        boolean z = VERSION.SDK_INT >= 23 && eVar.b;
        this.d = z;
        this.b = eVar.c;
        this.e = eVar.d;
        this.f = eVar.e;
        if (VERSION.SDK_INT >= 24) {
            this.i = eVar.h;
            this.g = eVar.f;
            this.h = eVar.g;
        }
    }

    public d(d dVar) {
        this.c = dVar.c;
        this.d = dVar.d;
        this.b = dVar.b;
        this.e = dVar.e;
        this.f = dVar.f;
        this.i = dVar.i;
    }

    public final r a() {
        return this.b;
    }

    public final void a(r rVar) {
        this.b = rVar;
    }

    public final boolean b() {
        return this.c;
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final boolean c() {
        return this.d;
    }

    public final void b(boolean z) {
        this.d = z;
    }

    public final boolean d() {
        return this.e;
    }

    public final void c(boolean z) {
        this.e = z;
    }

    public final boolean e() {
        return this.f;
    }

    public final void d(boolean z) {
        this.f = z;
    }

    public final long f() {
        return this.g;
    }

    public final void a(long j) {
        this.g = j;
    }

    public final long g() {
        return this.h;
    }

    public final void b(long j) {
        this.h = j;
    }

    public final void a(f fVar) {
        this.i = fVar;
    }

    public final f h() {
        return this.i;
    }

    public final boolean i() {
        return this.i.b() > 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.c == dVar.c && this.d == dVar.d && this.e == dVar.e && this.f == dVar.f && this.g == dVar.g && this.h == dVar.h && this.b == dVar.b) {
            return this.i.equals(dVar.i);
        }
        return false;
    }

    public final int hashCode() {
        return (((((((((((((this.b.hashCode() * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + ((int) (this.h ^ (this.h >>> 32)))) * 31) + this.i.hashCode();
    }
}
