package com.facebook.react.a;

import android.view.View;
import javax.annotation.Nullable;

public abstract class a {
    private final int a;
    private volatile boolean b;
    private volatile boolean c;
    @Nullable
    private b d;
    @Nullable
    private View e;

    public final void a(b animationListener) {
        this.d = animationListener;
    }

    public final void a(View view) {
        this.e = view;
    }

    public final void a() {
        if (!this.c && !this.b) {
            this.b = true;
            if (this.d != null) {
                this.d.a();
            }
        }
    }

    public final int b() {
        return this.a;
    }
}
