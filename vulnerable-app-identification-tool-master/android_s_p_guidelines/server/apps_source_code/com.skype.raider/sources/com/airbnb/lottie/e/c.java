package com.airbnb.lottie.e;

import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import com.airbnb.lottie.e;

public final class c extends a implements FrameCallback {
    protected boolean a = false;
    private float b = 1.0f;
    private long c = 0;
    private float d = 0.0f;
    private int e = 0;
    private float f = -2.14748365E9f;
    private float g = 2.14748365E9f;
    @Nullable
    private e h;

    public final Object getAnimatedValue() {
        return Float.valueOf(f());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public final float f() {
        if (this.h == null) {
            return 0.0f;
        }
        return (this.d - this.h.d()) / (this.h.e() - this.h.d());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public final float getAnimatedFraction() {
        if (this.h == null) {
            return 0.0f;
        }
        if (i()) {
            return (k() - this.d) / (k() - j());
        }
        return (this.d - j()) / (k() - j());
    }

    public final long getDuration() {
        return this.h == null ? 0 : (long) this.h.c();
    }

    public final boolean isRunning() {
        return this.a;
    }

    public final void doFrame(long frameTimeNanos) {
        l();
        if (this.h != null && isRunning()) {
            float frameDuration;
            Object obj;
            long now = System.nanoTime();
            long timeSinceFrame = now - this.c;
            if (this.h == null) {
                frameDuration = Float.MAX_VALUE;
            } else {
                frameDuration = (1.0E9f / this.h.f()) / Math.abs(this.b);
            }
            float dFrames = ((float) timeSinceFrame) / frameDuration;
            float f = this.d;
            if (i()) {
                dFrames = -dFrames;
            }
            this.d = f + dFrames;
            f = this.d;
            float j = j();
            float k = k();
            if (f < j || f > k) {
                obj = null;
            } else {
                obj = 1;
            }
            boolean ended = obj == null;
            this.d = e.a(this.d, j(), k());
            this.c = now;
            e();
            if (ended) {
                if (getRepeatCount() == -1 || this.e < getRepeatCount()) {
                    b();
                    this.e++;
                    if (getRepeatMode() == 2) {
                        this.b = -this.b;
                    } else {
                        this.d = i() ? k() : j();
                    }
                    this.c = now;
                } else {
                    this.d = k();
                    c();
                    m();
                }
            }
            if (this.h == null) {
                return;
            }
            if (this.d < this.f || this.d > this.g) {
                throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", new Object[]{Float.valueOf(this.f), Float.valueOf(this.g), Float.valueOf(this.d)}));
            }
        }
    }

    public final void a(e composition) {
        this.h = composition;
        a((int) composition.d(), (int) composition.e());
        a((int) this.d);
        this.c = System.nanoTime();
    }

    public final void a(int frame) {
        if (this.d != ((float) frame)) {
            this.d = e.a((float) frame, j(), k());
            this.c = System.nanoTime();
            e();
        }
    }

    public final void b(int minFrame) {
        a(minFrame, (int) this.g);
    }

    public final void c(int maxFrame) {
        a((int) this.f, maxFrame);
    }

    public final void a(int minFrame, int maxFrame) {
        this.f = (float) minFrame;
        this.g = (float) maxFrame;
        a((int) e.a(this.d, (float) minFrame, (float) maxFrame));
    }

    public final void a(float speed) {
        this.b = speed;
    }

    public final void g() {
        this.a = true;
        a();
        a((int) (i() ? k() : j()));
        this.c = System.nanoTime();
        this.e = 0;
        l();
    }

    public final void h() {
        m();
        c();
    }

    public final void cancel() {
        d();
        m();
    }

    private boolean i() {
        return this.b < 0.0f;
    }

    private float j() {
        if (this.h == null) {
            return 0.0f;
        }
        return this.f == -2.14748365E9f ? this.h.d() : this.f;
    }

    private float k() {
        if (this.h == null) {
            return 0.0f;
        }
        return this.g == 2.14748365E9f ? this.h.e() : this.g;
    }

    private void l() {
        boolean wasRunning = this.a;
        m();
        if (wasRunning) {
            this.a = true;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    private void m() {
        Choreographer.getInstance().removeFrameCallback(this);
        this.a = false;
    }
}
