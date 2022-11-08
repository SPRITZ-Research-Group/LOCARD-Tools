package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams;
import java.util.ArrayList;

final class ci {
    ArrayList<View> a = new ArrayList();
    int b = Integer.MIN_VALUE;
    int c = Integer.MIN_VALUE;
    int d = 0;
    final int e;
    final /* synthetic */ StaggeredGridLayoutManager f;

    ci(StaggeredGridLayoutManager staggeredGridLayoutManager, int i) {
        this.f = staggeredGridLayoutManager;
        this.e = i;
    }

    final int a(int i) {
        if (this.b != Integer.MIN_VALUE) {
            return this.b;
        }
        if (this.a.size() == 0) {
            return i;
        }
        h();
        return this.b;
    }

    private void h() {
        View view = (View) this.a.get(0);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        this.b = this.f.b.a(view);
        if (layoutParams.b) {
            FullSpanItem d = this.f.h.d(layoutParams.c.getLayoutPosition());
            if (d != null && d.b == -1) {
                this.b -= d.a(this.e);
            }
        }
    }

    final int a() {
        if (this.b != Integer.MIN_VALUE) {
            return this.b;
        }
        h();
        return this.b;
    }

    final int b(int i) {
        if (this.c != Integer.MIN_VALUE) {
            return this.c;
        }
        if (this.a.size() == 0) {
            return i;
        }
        i();
        return this.c;
    }

    private void i() {
        View view = (View) this.a.get(this.a.size() - 1);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        this.c = this.f.b.b(view);
        if (layoutParams.b) {
            FullSpanItem d = this.f.h.d(layoutParams.c.getLayoutPosition());
            if (d != null && d.b == 1) {
                this.c += d.a(this.e);
            }
        }
    }

    final int b() {
        if (this.c != Integer.MIN_VALUE) {
            return this.c;
        }
        i();
        return this.c;
    }

    final void c() {
        this.a.clear();
        this.b = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.d = 0;
    }

    final void c(int i) {
        this.b = i;
        this.c = i;
    }

    final void d() {
        int size = this.a.size();
        View view = (View) this.a.remove(size - 1);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.a = null;
        if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
            this.d -= this.f.b.e(view);
        }
        if (size == 1) {
            this.b = Integer.MIN_VALUE;
        }
        this.c = Integer.MIN_VALUE;
    }

    final void e() {
        View view = (View) this.a.remove(0);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.a = null;
        if (this.a.size() == 0) {
            this.c = Integer.MIN_VALUE;
        }
        if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
            this.d -= this.f.b.e(view);
        }
        this.b = Integer.MIN_VALUE;
    }

    final void d(int i) {
        if (this.b != Integer.MIN_VALUE) {
            this.b += i;
        }
        if (this.c != Integer.MIN_VALUE) {
            this.c += i;
        }
    }

    public final int f() {
        if (this.f.d) {
            return c(this.a.size() - 1, -1);
        }
        return c(0, this.a.size());
    }

    public final int g() {
        if (this.f.d) {
            return c(0, this.a.size());
        }
        return c(this.a.size() - 1, -1);
    }

    private int a(int i, int i2, boolean z, boolean z2) {
        int c = this.f.b.c();
        int d = this.f.b.d();
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View view = (View) this.a.get(i);
            int a = this.f.b.a(view);
            int b = this.f.b.b(view);
            Object obj = null;
            Object obj2 = (z2 ? a > d : a >= d) ? null : 1;
            if (z2 ? b < c : b <= c) {
                obj = 1;
            }
            if (!(obj2 == null || obj == null)) {
                if (z) {
                    return bj.d(view);
                }
                if (a < c || b > d) {
                    return bj.d(view);
                }
            }
            i += i3;
        }
        return -1;
    }

    final int a(int i, int i2) {
        return a(i, i2, true, false);
    }

    private int c(int i, int i2) {
        return a(i, i2, false, true);
    }

    public final View b(int i, int i2) {
        View view = null;
        if (i2 != -1) {
            i2 = this.a.size() - 1;
            while (i2 >= 0) {
                View view2 = (View) this.a.get(i2);
                if ((this.f.d && bj.d(view2) >= i) || ((!this.f.d && bj.d(view2) <= i) || !view2.hasFocusable())) {
                    break;
                }
                i2--;
                view = view2;
            }
        } else {
            i2 = this.a.size();
            int i3 = 0;
            while (i3 < i2) {
                View view3 = (View) this.a.get(i3);
                if ((this.f.d && bj.d(view3) <= i) || ((!this.f.d && bj.d(view3) >= i) || !view3.hasFocusable())) {
                    break;
                }
                i3++;
                view = view3;
            }
        }
        return view;
    }

    final void a(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.a = this;
        this.a.add(0, view);
        this.b = Integer.MIN_VALUE;
        if (this.a.size() == 1) {
            this.c = Integer.MIN_VALUE;
        }
        if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
            this.d += this.f.b.e(view);
        }
    }

    final void b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.a = this;
        this.a.add(view);
        this.c = Integer.MIN_VALUE;
        if (this.a.size() == 1) {
            this.b = Integer.MIN_VALUE;
        }
        if (layoutParams.c.isRemoved() || layoutParams.c.isUpdated()) {
            this.d += this.f.b.e(view);
        }
    }
}
