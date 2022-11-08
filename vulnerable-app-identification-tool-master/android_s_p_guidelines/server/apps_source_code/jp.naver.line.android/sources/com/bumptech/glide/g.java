package com.bumptech.glide;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import java.util.List;

public final class g<T> implements OnScrollListener {
    private final int a;
    private final k b;
    private final w c;
    private final h<T> d;
    private final i<T> e;
    private int f;
    private int g;
    private int h = -1;
    private int i;
    private boolean j = true;

    public final void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public g(w wVar, h<T> hVar, i<T> iVar, int i) {
        this.c = wVar;
        this.d = hVar;
        this.e = iVar;
        this.a = i;
        this.b = new k(i + 1);
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.i = i3;
        if (i > this.h) {
            a(i2 + i, true);
        } else if (i < this.h) {
            a(i, false);
        }
        this.h = i;
    }

    private void a(int i, boolean z) {
        if (this.j != z) {
            this.j = z;
            a();
        }
        a(i, (z ? this.a : -this.a) + i);
    }

    private void a(int i, int i2) {
        int max;
        int i3;
        if (i < i2) {
            max = Math.max(this.f, i);
            i3 = i2;
        } else {
            i3 = Math.min(this.g, i);
            max = i2;
        }
        i3 = Math.min(this.i, i3);
        max = Math.min(this.i, Math.max(0, max));
        if (i < i2) {
            for (i = max; i < i3; i++) {
                a(this.d.b(i), true);
            }
        } else {
            for (i = i3 - 1; i >= max; i--) {
                a(this.d.b(i), false);
            }
        }
        this.g = max;
        this.f = i3;
    }

    private void a(List<T> list, boolean z) {
        int size = list.size();
        if (z) {
            for (int i = 0; i < size; i++) {
                a(list.get(i));
            }
            return;
        }
        for (size--; size >= 0; size--) {
            a(list.get(size));
        }
    }

    private void a(T t) {
        if (t != null) {
            int[] a = this.e.a();
            if (a != null) {
                v a2 = this.d.a(t);
                if (a2 != null) {
                    a2.a(this.b.a(a[0], a[1]));
                }
            }
        }
    }

    private void a() {
        for (int i = 0; i < this.a; i++) {
            this.c.a(this.b.a(0, 0));
        }
    }
}
