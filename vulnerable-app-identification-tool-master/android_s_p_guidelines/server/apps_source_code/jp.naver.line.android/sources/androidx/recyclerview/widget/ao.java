package androidx.recyclerview.widget;

import android.view.View;

final class ao {
    ax a;
    int b;
    int c;
    boolean d;
    boolean e;

    ao() {
        a();
    }

    final void a() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    final void b() {
        int d;
        if (this.d) {
            d = this.a.d();
        } else {
            d = this.a.c();
        }
        this.c = d;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("AnchorInfo{mPosition=");
        stringBuilder.append(this.b);
        stringBuilder.append(", mCoordinate=");
        stringBuilder.append(this.c);
        stringBuilder.append(", mLayoutFromEnd=");
        stringBuilder.append(this.d);
        stringBuilder.append(", mValid=");
        stringBuilder.append(this.e);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public final void a(View view, int i) {
        int b = this.a.b();
        if (b >= 0) {
            b(view, i);
            return;
        }
        this.b = i;
        int e;
        if (this.d) {
            i = (this.a.d() - b) - this.a.b(view);
            this.c = this.a.d() - i;
            if (i > 0) {
                e = this.c - this.a.e(view);
                b = this.a.c();
                e -= b + Math.min(this.a.a(view) - b, 0);
                if (e < 0) {
                    this.c += Math.min(i, -e);
                }
            }
            return;
        }
        i = this.a.a(view);
        e = i - this.a.c();
        this.c = i;
        if (e > 0) {
            int d = (this.a.d() - Math.min(0, (this.a.d() - b) - this.a.b(view))) - (i + this.a.e(view));
            if (d < 0) {
                this.c -= Math.min(e, -d);
            }
        }
    }

    public final void b(View view, int i) {
        if (this.d) {
            this.c = this.a.b(view) + this.a.b();
        } else {
            this.c = this.a.a(view);
        }
        this.b = i;
    }
}
