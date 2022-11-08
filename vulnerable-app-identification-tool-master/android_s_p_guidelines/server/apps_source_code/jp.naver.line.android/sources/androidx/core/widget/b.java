package androidx.core.widget;

import android.view.animation.AnimationUtils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class b {
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

    b() {
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
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.k = a.a((int) (currentAnimationTimeMillis - this.e), this.b);
        this.j = a(currentAnimationTimeMillis);
        this.i = currentAnimationTimeMillis;
    }

    public final boolean e() {
        return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
    }

    private float a(long j) {
        if (j < this.e) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (this.i < 0 || j < this.i) {
            return a.a(((float) (j - this.e)) / ((float) this.a), BitmapDescriptorFactory.HUE_RED, 1.0f) * 0.5f;
        }
        return (1.0f - this.j) + (this.j * a.a(((float) (j - this.i)) / ((float) this.k), BitmapDescriptorFactory.HUE_RED, 1.0f));
    }

    public final void f() {
        if (this.f != 0) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float a = a(currentAnimationTimeMillis);
            float f = ((-4.0f * a) * a) + (a * 4.0f);
            long j = currentAnimationTimeMillis - this.f;
            this.f = currentAnimationTimeMillis;
            float f2 = ((float) j) * f;
            this.g = (int) (this.c * f2);
            this.h = (int) (f2 * this.d);
            return;
        }
        throw new RuntimeException("Cannot compute scroll delta before calling start()");
    }

    public final void a(float f, float f2) {
        this.c = f;
        this.d = f2;
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
