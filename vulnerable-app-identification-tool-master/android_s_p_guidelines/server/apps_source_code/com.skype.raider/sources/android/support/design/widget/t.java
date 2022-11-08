package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

final class t extends e {
    private static final Handler a = new Handler(Looper.getMainLooper());
    private long b;
    private boolean c;
    private final int[] d = new int[2];
    private final float[] e = new float[2];
    private int f = 200;
    private Interpolator g;
    private a h;
    private b i;
    private float j;
    private final Runnable k = new Runnable(this) {
        final /* synthetic */ t a;

        {
            this.a = r1;
        }

        public final void run() {
            t.a(this.a);
        }
    };

    t() {
    }

    public final void a() {
        if (!this.c) {
            if (this.g == null) {
                this.g = new AccelerateDecelerateInterpolator();
            }
            this.b = SystemClock.uptimeMillis();
            this.c = true;
            a.postDelayed(this.k, 10);
        }
    }

    public final boolean b() {
        return this.c;
    }

    public final void a(Interpolator interpolator) {
        this.g = interpolator;
    }

    public final void a(a listener) {
        this.h = listener;
    }

    public final void a(b updateListener) {
        this.i = updateListener;
    }

    public final void a(int from, int to) {
        this.d[0] = from;
        this.d[1] = to;
    }

    public final int c() {
        return a.a(this.d[0], this.d[1], this.j);
    }

    public final void a(float from, float to) {
        this.e[0] = from;
        this.e[1] = to;
    }

    public final float d() {
        return a.a(this.e[0], this.e[1], this.j);
    }

    public final void a(int duration) {
        this.f = duration;
    }

    public final void e() {
        this.c = false;
        a.removeCallbacks(this.k);
        if (this.h != null) {
            this.h.b();
        }
    }

    public final float f() {
        return this.j;
    }

    public final long g() {
        return (long) this.f;
    }

    static /* synthetic */ void a(t x0) {
        if (x0.c) {
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - x0.b)) / ((float) x0.f);
            if (x0.g != null) {
                uptimeMillis = x0.g.getInterpolation(uptimeMillis);
            }
            x0.j = uptimeMillis;
            if (x0.i != null) {
                x0.i.a();
            }
            if (SystemClock.uptimeMillis() >= x0.b + ((long) x0.f)) {
                x0.c = false;
                if (x0.h != null) {
                    x0.h.a();
                }
            }
        }
        if (x0.c) {
            a.postDelayed(x0.k, 10);
        }
    }
}
