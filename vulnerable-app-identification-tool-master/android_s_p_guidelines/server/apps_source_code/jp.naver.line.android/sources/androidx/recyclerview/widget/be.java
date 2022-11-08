package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.List;

public abstract class be {
    private bf a = null;
    private ArrayList<Object> b = new ArrayList();
    private long c = 120;
    private long d = 120;
    private long e = 250;
    private long f = 250;

    public abstract void a();

    public abstract boolean a(cb cbVar, bg bgVar, bg bgVar2);

    public abstract boolean a(cb cbVar, cb cbVar2, bg bgVar, bg bgVar2);

    public abstract boolean b();

    public abstract boolean b(cb cbVar, bg bgVar, bg bgVar2);

    public abstract void c(cb cbVar);

    public abstract boolean c(cb cbVar, bg bgVar, bg bgVar2);

    public abstract void d();

    public boolean g(cb cbVar) {
        return true;
    }

    public final long e() {
        return this.e;
    }

    public final void f() {
        this.e = 100;
    }

    public long g() {
        return this.c;
    }

    public final void h() {
        this.c = 100;
    }

    public final long i() {
        return this.d;
    }

    public final void j() {
        this.d = 100;
    }

    public final long k() {
        return this.f;
    }

    public final void a(long j) {
        this.f = j;
    }

    final void a(bf bfVar) {
        this.a = bfVar;
    }

    static int e(cb cbVar) {
        int i = cbVar.mFlags & 14;
        if (cbVar.isInvalid()) {
            return 4;
        }
        if ((i & 4) == 0) {
            int oldPosition = cbVar.getOldPosition();
            int adapterPosition = cbVar.getAdapterPosition();
            if (!(oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition)) {
                i |= 2048;
            }
        }
        return i;
    }

    public final void f(cb cbVar) {
        if (this.a != null) {
            this.a.a(cbVar);
        }
    }

    public boolean a(cb cbVar, List<Object> list) {
        return g(cbVar);
    }

    public final void l() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.b.get(i);
        }
        this.b.clear();
    }

    public static bg d(cb cbVar) {
        return new bg().a(cbVar);
    }
}
