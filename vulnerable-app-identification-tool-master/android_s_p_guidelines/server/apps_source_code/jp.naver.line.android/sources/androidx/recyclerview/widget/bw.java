package androidx.recyclerview.widget;

import android.util.Log;
import android.view.animation.Interpolator;

public final class bw {
    private int a;
    private int b;
    private int c;
    private int d;
    private Interpolator e;
    private boolean f;
    private int g;

    public bw() {
        this((byte) 0);
    }

    private bw(byte b) {
        this.d = -1;
        this.f = false;
        this.g = 0;
        this.a = 0;
        this.b = 0;
        this.c = Integer.MIN_VALUE;
        this.e = null;
    }

    public final void a(int i) {
        this.d = i;
    }

    final boolean a() {
        return this.d >= 0;
    }

    final void a(RecyclerView recyclerView) {
        if (this.d >= 0) {
            int i = this.d;
            this.d = -1;
            recyclerView.jumpToPositionForSmoothScroller(i);
            this.f = false;
        } else if (!this.f) {
            this.g = 0;
        } else if (this.e != null && this.c <= 0) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        } else if (this.c > 0) {
            if (this.e != null) {
                recyclerView.mViewFlinger.a(this.a, this.b, this.c, this.e);
            } else if (this.c == Integer.MIN_VALUE) {
                recyclerView.mViewFlinger.b(this.a, this.b);
            } else {
                recyclerView.mViewFlinger.a(this.a, this.b, this.c);
            }
            this.g++;
            if (this.g > 10) {
                Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
            }
            this.f = false;
        } else {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
    }

    public final void a(int i, int i2, int i3, Interpolator interpolator) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.e = interpolator;
        this.f = true;
    }
}
