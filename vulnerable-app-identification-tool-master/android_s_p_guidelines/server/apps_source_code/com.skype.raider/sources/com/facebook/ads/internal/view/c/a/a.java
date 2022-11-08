package com.facebook.ads.internal.view.c.a;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.k;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.z;
import android.view.View;
import com.facebook.ads.internal.view.component.a.a.b;
import com.facebook.ads.internal.view.component.a.a.b.c;
import com.facebook.ads.internal.view.component.a.a.b.d;
import com.facebook.ads.internal.view.component.a.a.b.e;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class a extends k {
    private final LinearLayoutManager a;
    private final int b;
    private final p c;
    private final Set<Integer> d = new HashSet();
    private List<b> e;
    private final com.facebook.ads.internal.s.a f;
    private boolean g = true;
    @Nullable
    private com.facebook.ads.internal.view.c.a.d.a h;
    private boolean i = true;
    private boolean j = true;
    private boolean k;
    private final e l = new e(this) {
        final /* synthetic */ a a;
        private float b = 0.0f;

        {
            this.a = r2;
        }

        public final float a() {
            return this.b;
        }

        public final void a(float f) {
            this.b = f;
        }
    };
    private final c m = new c(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void a(int i) {
            this.a.a(i, true);
            if (this.a.e()) {
                a.b(this.a);
            } else {
                a.b(this.a, i);
            }
        }
    };
    private final d n = new d(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void a() {
            if (this.a.k) {
                this.a.j = false;
            }
        }

        public final void a(View view) {
            b bVar = (b) view;
            bVar.j();
            if (this.a.k) {
                this.a.j = true;
            }
            if (this.a.f.b() && ((Integer) bVar.getTag(-1593835536)).intValue() == 0) {
                this.a.f.a();
            }
        }
    };

    a(c cVar, int i, List<b> list, com.facebook.ads.internal.s.a aVar) {
        this.a = cVar.t();
        this.b = i;
        this.e = list;
        this.f = aVar;
        this.c = new z(cVar.getContext());
        cVar.a((k) this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @Nullable
    private b a(int i, int i2, boolean z) {
        b bVar = null;
        while (i <= i2) {
            View view = (b) this.a.c(i);
            if (view.g()) {
                return null;
            }
            boolean a = a(view);
            if (bVar == null && view.f() && a && !this.d.contains(Integer.valueOf(i))) {
                if (z) {
                }
                bVar = view;
            }
            if (view.f() && !a) {
                a(i, false);
            }
            i++;
        }
        return bVar;
    }

    private void a(int i) {
        this.c.d(i);
        this.a.a(this.c);
    }

    private void a(int i, boolean z) {
        if (z) {
            this.d.add(Integer.valueOf(i));
        } else {
            this.d.remove(Integer.valueOf(i));
        }
    }

    private void a(b bVar, boolean z) {
        if (e()) {
            bVar.setAlpha(z ? 1.0f : 0.5f);
        }
        if (!z && bVar.g()) {
            bVar.i();
        }
    }

    private static boolean a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return ((float) rect.width()) / ((float) view.getWidth()) >= 0.15f;
    }

    private void b(int i) {
        b bVar = (b) this.a.c(i);
        if (!a((View) bVar)) {
            a(bVar, false);
        }
    }

    private void d() {
        if (this.j) {
            b a = a(this.a.k(), this.a.m(), true);
            if (a != null) {
                a.h();
            }
        }
    }

    private boolean e() {
        return this.b == 1;
    }

    public final e a() {
        return this.l;
    }

    public final void a(RecyclerView recyclerView, int i) {
        super.a(recyclerView, i);
        if (i == 0) {
            this.k = true;
            d();
        }
    }

    public final void a(RecyclerView recyclerView, int i, int i2) {
        super.a(recyclerView, i, i2);
        this.k = false;
        if (this.i) {
            this.k = true;
            d();
            this.i = false;
        }
        int k = this.a.k();
        int m = this.a.m();
        b(k);
        b(m);
        for (int i3 = k; i3 <= m; i3++) {
            boolean z;
            b bVar = (b) this.a.c(i3);
            if (a((View) bVar)) {
                a(bVar, true);
            }
            if (this.g && bVar.f()) {
                this.g = false;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                float f;
                b bVar2 = (b) this.e.get(((Integer) bVar.getTag(-1593835536)).intValue());
                e eVar = this.l;
                if (bVar2.c().c().e()) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                eVar.a(f);
            }
        }
        if (e() && this.h != null) {
            int l = this.a.l();
            if (l == -1) {
                l = i < 0 ? k : m;
            }
            this.h.a(l);
        }
    }

    final void a(com.facebook.ads.internal.view.c.a.d.a aVar) {
        this.h = aVar;
    }

    public final c b() {
        return this.m;
    }

    public final d c() {
        return this.n;
    }

    static /* synthetic */ void b(a aVar) {
        int l = aVar.a.l();
        if (l != -1 && l < aVar.e.size() - 1) {
            aVar.a(l + 1);
        }
    }

    static /* synthetic */ void b(a aVar, int i) {
        b a = aVar.a(i + 1, aVar.a.m(), false);
        if (a != null) {
            a.h();
            aVar.a(((Integer) a.getTag(-1593835536)).intValue());
        }
    }
}
