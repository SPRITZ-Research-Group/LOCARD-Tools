package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

final class j {
    final l a;
    final k b = new k();
    final List<View> c = new ArrayList();

    j(l lVar) {
        this.a = lVar;
    }

    private void h(View view) {
        this.c.add(view);
        this.a.c(view);
    }

    private boolean i(View view) {
        if (!this.c.remove(view)) {
            return false;
        }
        this.a.d(view);
        return true;
    }

    final void a(View view) {
        a(view, -1, true);
    }

    final void a(View view, int i, boolean z) {
        if (i < 0) {
            i = this.a.a();
        } else {
            i = e(i);
        }
        this.b.a(i, z);
        if (z) {
            h(view);
        }
        this.a.a(view, i);
    }

    private int e(int i) {
        if (i < 0) {
            return -1;
        }
        int a = this.a.a();
        int i2 = i;
        while (i2 < a) {
            int e = i - (i2 - this.b.e(i2));
            if (e == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += e;
        }
        return -1;
    }

    final void b(View view) {
        int a = this.a.a(view);
        if (a >= 0) {
            if (this.b.d(a)) {
                i(view);
            }
            this.a.a(a);
        }
    }

    final void a(int i) {
        i = e(i);
        View b = this.a.b(i);
        if (b != null) {
            if (this.b.d(i)) {
                i(b);
            }
            this.a.a(i);
        }
    }

    final View b(int i) {
        return this.a.b(e(i));
    }

    final void a(View view, int i, LayoutParams layoutParams, boolean z) {
        if (i < 0) {
            i = this.a.a();
        } else {
            i = e(i);
        }
        this.b.a(i, z);
        if (z) {
            h(view);
        }
        this.a.a(view, i, layoutParams);
    }

    final int a() {
        return this.a.a() - this.c.size();
    }

    final int b() {
        return this.a.a();
    }

    final View c(int i) {
        return this.a.b(i);
    }

    final void d(int i) {
        i = e(i);
        this.b.d(i);
        this.a.c(i);
    }

    final int c(View view) {
        int a = this.a.a(view);
        if (a == -1 || this.b.c(a)) {
            return -1;
        }
        return a - this.b.e(a);
    }

    final boolean d(View view) {
        return this.c.contains(view);
    }

    final void e(View view) {
        int a = this.a.a(view);
        if (a >= 0) {
            this.b.a(a);
            h(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide ".concat(String.valueOf(view)));
    }

    final void f(View view) {
        int a = this.a.a(view);
        if (a < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide ".concat(String.valueOf(view)));
        } else if (this.b.c(a)) {
            this.b.b(a);
            i(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden".concat(String.valueOf(view)));
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b.toString());
        stringBuilder.append(", hidden list:");
        stringBuilder.append(this.c.size());
        return stringBuilder.toString();
    }

    final boolean g(View view) {
        int a = this.a.a(view);
        if (a == -1) {
            i(view);
            return true;
        } else if (!this.b.c(a)) {
            return false;
        } else {
            this.b.d(a);
            i(view);
            this.a.a(a);
            return true;
        }
    }
}
