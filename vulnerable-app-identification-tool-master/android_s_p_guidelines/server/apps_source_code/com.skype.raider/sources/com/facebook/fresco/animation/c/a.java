package com.facebook.fresco.animation.c;

import com.facebook.fresco.animation.a.d;

public final class a implements b {
    private final d a;
    private long b = -1;

    public a(d animationInformation) {
        this.a = animationInformation;
    }

    public final int a(long animationTimeMs) {
        if (!b() && animationTimeMs / a() >= ((long) this.a.e())) {
            return -1;
        }
        int i = 0;
        long j = 0;
        do {
            j += (long) this.a.b(i);
            i++;
        } while (animationTimeMs % a() >= j);
        return i - 1;
    }

    private long a() {
        if (this.b != -1) {
            return this.b;
        }
        this.b = 0;
        int frameCount = this.a.d();
        for (int i = 0; i < frameCount; i++) {
            this.b += (long) this.a.b(i);
        }
        return this.b;
    }

    public final long b(long animationTimeMs) {
        long loopDurationMs = a();
        if (loopDurationMs == 0) {
            return -1;
        }
        if (!b() && animationTimeMs / a() >= ((long) this.a.e())) {
            return -1;
        }
        long timePassedInCurrentLoopMs = animationTimeMs % loopDurationMs;
        long timeOfNextFrameInLoopMs = 0;
        int frameCount = this.a.d();
        for (int i = 0; i < frameCount && timeOfNextFrameInLoopMs <= timePassedInCurrentLoopMs; i++) {
            timeOfNextFrameInLoopMs += (long) this.a.b(i);
        }
        return animationTimeMs + (timeOfNextFrameInLoopMs - timePassedInCurrentLoopMs);
    }

    private boolean b() {
        return this.a.e() == 0;
    }
}
