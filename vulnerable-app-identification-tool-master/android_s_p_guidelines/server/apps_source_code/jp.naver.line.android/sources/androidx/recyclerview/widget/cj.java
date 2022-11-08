package androidx.recyclerview.widget;

import android.view.View;

final class cj {
    final cl a;
    ck b = new ck();

    cj(cl clVar) {
        this.a = clVar;
    }

    final View a(int i, int i2, int i3, int i4) {
        int a = this.a.a();
        int b = this.a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View a2 = this.a.a(i);
            this.b.a(a, b, this.a.a(a2), this.a.b(a2));
            if (i3 != 0) {
                this.b.a = 0;
                this.b.a(i3);
                if (this.b.a()) {
                    return a2;
                }
            }
            if (i4 != 0) {
                this.b.a = 0;
                this.b.a(i4);
                if (this.b.a()) {
                    view = a2;
                }
            }
            i += i5;
        }
        return view;
    }

    final boolean a(View view) {
        this.b.a(this.a.a(), this.a.b(), this.a.a(view), this.a.b(view));
        this.b.a = 0;
        this.b.a(24579);
        return this.b.a();
    }
}
