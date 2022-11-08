package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public abstract class a implements OnTouchListener {
    private static final int r = ViewConfiguration.getTapTimeout();
    final a a = new a();
    final View b;
    boolean c;
    boolean d;
    boolean e;
    private final Interpolator f = new AccelerateInterpolator();
    private Runnable g;
    private float[] h = new float[]{0.0f, 0.0f};
    private float[] i = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
    private int j;
    private int k;
    private float[] l = new float[]{0.0f, 0.0f};
    private float[] m = new float[]{0.0f, 0.0f};
    private float[] n = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
    private boolean o;
    private boolean p;
    private boolean q;

    private static class a {
        private int a;
        private int b;
        private float c;
        private float d;
        private long e = Long.MIN_VALUE;
        private long f = 0;
        private int g = 0;
        private int h = 0;
        private long i = -1;
        private float j;
        private int k;

        a() {
        }

        public final void a() {
            this.a = 500;
        }

        public final void b() {
            this.b = 500;
        }

        public final void c() {
            this.e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1;
            this.f = this.e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        public final void d() {
            long currentTime = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a((int) (currentTime - this.e), this.b);
            this.j = a(currentTime);
            this.i = currentTime;
        }

        public final boolean e() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        private float a(long currentTime) {
            if (currentTime < this.e) {
                return 0.0f;
            }
            if (this.i < 0 || currentTime < this.i) {
                return a.a(((float) (currentTime - this.e)) / ((float) this.a), 0.0f, 1.0f) * 0.5f;
            }
            long elapsedSinceEnd = currentTime - this.i;
            return (a.a(((float) elapsedSinceEnd) / ((float) this.k), 0.0f, 1.0f) * this.j) + (1.0f - this.j);
        }

        public final void f() {
            if (this.f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentTime = AnimationUtils.currentAnimationTimeMillis();
            float a = a(currentTime);
            float scale = ((-4.0f * a) * a) + (a * 4.0f);
            long elapsedSinceDelta = currentTime - this.f;
            this.f = currentTime;
            this.g = (int) ((((float) elapsedSinceDelta) * scale) * this.c);
            this.h = (int) ((((float) elapsedSinceDelta) * scale) * this.d);
        }

        public final void a(float x, float y) {
            this.c = x;
            this.d = y;
        }

        public final int g() {
            return (int) (this.c / Math.abs(this.c));
        }

        public final int h() {
            return (int) (this.d / Math.abs(this.d));
        }

        public final int i() {
            return this.h;
        }
    }

    private class b implements Runnable {
        final /* synthetic */ a a;

        b(a aVar) {
            this.a = aVar;
        }

        public final void run() {
            if (this.a.e) {
                if (this.a.c) {
                    this.a.c = false;
                    this.a.a.c();
                }
                a scroller = this.a.a;
                if (scroller.e() || !this.a.a()) {
                    this.a.e = false;
                    return;
                }
                if (this.a.d) {
                    this.a.d = false;
                    a aVar = this.a;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    aVar.b.onTouchEvent(obtain);
                    obtain.recycle();
                }
                scroller.f();
                this.a.a(scroller.i());
                ViewCompat.a(this.a.b, (Runnable) this);
            }
        }
    }

    public abstract void a(int i);

    public abstract boolean b(int i);

    public a(View target) {
        this.b = target;
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int minVelocity = (int) ((315.0f * metrics.density) + 0.5f);
        float f = (float) ((int) ((1575.0f * metrics.density) + 0.5f));
        this.n[0] = f / 1000.0f;
        this.n[1] = f / 1000.0f;
        f = (float) minVelocity;
        this.m[0] = f / 1000.0f;
        this.m[1] = f / 1000.0f;
        this.j = 1;
        this.i[0] = Float.MAX_VALUE;
        this.i[1] = Float.MAX_VALUE;
        this.h[0] = 0.2f;
        this.h[1] = 0.2f;
        this.l[0] = 0.001f;
        this.l[1] = 0.001f;
        this.k = r;
        this.a.a();
        this.a.b();
    }

    public final a a(boolean enabled) {
        if (this.p && !enabled) {
            b();
        }
        this.p = enabled;
        return this;
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (!this.p) {
            return false;
        }
        switch (event.getActionMasked()) {
            case 0:
                this.d = true;
                this.o = false;
                break;
            case 1:
            case 3:
                b();
                break;
            case 2:
                break;
        }
        this.a.a(a(0, event.getX(), (float) v.getWidth(), (float) this.b.getWidth()), a(1, event.getY(), (float) v.getHeight(), (float) this.b.getHeight()));
        if (!this.e && a()) {
            if (this.g == null) {
                this.g = new b(this);
            }
            this.e = true;
            this.c = true;
            if (this.o || this.k <= 0) {
                this.g.run();
            } else {
                ViewCompat.a(this.b, this.g, (long) this.k);
            }
            this.o = true;
        }
        if (this.q && this.e) {
            return true;
        }
        return false;
    }

    final boolean a() {
        a scroller = this.a;
        int verticalDirection = scroller.h();
        int horizontalDirection = scroller.g();
        if (verticalDirection != 0 && b(verticalDirection)) {
            return true;
        }
        if (horizontalDirection != 0) {
        }
        return false;
    }

    private void b() {
        if (this.c) {
            this.e = false;
        } else {
            this.a.d();
        }
    }

    private float a(int direction, float coordinate, float srcSize, float dstSize) {
        float value;
        float relativeVelocity;
        float minimumVelocity;
        float maximumVelocity;
        float targetVelocity;
        float relativeEdge = this.h[direction];
        float a = a(relativeEdge * srcSize, 0.0f, this.i[direction]);
        a = a(srcSize - coordinate, a) - a(coordinate, a);
        if (a < 0.0f) {
            a = -this.f.getInterpolation(-a);
        } else if (a > 0.0f) {
            a = this.f.getInterpolation(a);
        } else {
            value = 0.0f;
            if (value == 0.0f) {
                return 0.0f;
            }
            relativeVelocity = this.l[direction];
            minimumVelocity = this.m[direction];
            maximumVelocity = this.n[direction];
            targetVelocity = relativeVelocity * dstSize;
            if (value <= 0.0f) {
                return a(value * targetVelocity, minimumVelocity, maximumVelocity);
            }
            return -a((-value) * targetVelocity, minimumVelocity, maximumVelocity);
        }
        value = a(a, -1.0f, 1.0f);
        if (value == 0.0f) {
            return 0.0f;
        }
        relativeVelocity = this.l[direction];
        minimumVelocity = this.m[direction];
        maximumVelocity = this.n[direction];
        targetVelocity = relativeVelocity * dstSize;
        if (value <= 0.0f) {
            return -a((-value) * targetVelocity, minimumVelocity, maximumVelocity);
        }
        return a(value * targetVelocity, minimumVelocity, maximumVelocity);
    }

    private float a(float current, float leading) {
        if (leading == 0.0f) {
            return 0.0f;
        }
        switch (this.j) {
            case 0:
            case 1:
                if (current >= leading) {
                    return 0.0f;
                }
                if (current >= 0.0f) {
                    return 1.0f - (current / leading);
                }
                if (this.e && this.j == 1) {
                    return 1.0f;
                }
                return 0.0f;
            case 2:
                if (current < 0.0f) {
                    return current / (-leading);
                }
                return 0.0f;
            default:
                return 0.0f;
        }
    }

    static int a(int value, int max) {
        if (value > max) {
            return max;
        }
        return value < 0 ? 0 : value;
    }

    static float a(float value, float min, float max) {
        if (value > max) {
            return max;
        }
        if (value < min) {
            return min;
        }
        return value;
    }
}
