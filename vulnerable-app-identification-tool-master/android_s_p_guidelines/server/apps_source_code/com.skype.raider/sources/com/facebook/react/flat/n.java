package com.facebook.react.flat;

import android.graphics.drawable.Drawable.Callback;
import com.facebook.drawee.d.a;
import com.facebook.drawee.interfaces.DraweeController;

final class n {
    private final DraweeController a;
    private int b;

    final void a(a callback) {
        this.b++;
        if (this.b == 1) {
            b().a().setCallback((Callback) callback.get());
            this.a.j();
        }
    }

    final void a() {
        this.b--;
        if (this.b == 0) {
            this.a.k();
        }
    }

    final a b() {
        return (a) this.a.h();
    }
}
