package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import java.util.Arrays;

public final class f extends a {
    @VisibleForTesting
    int a;
    @VisibleForTesting
    int b;
    @VisibleForTesting
    long c;
    @VisibleForTesting
    int[] d;
    @VisibleForTesting
    int[] e;
    @VisibleForTesting
    int f;
    @VisibleForTesting
    boolean[] g;
    @VisibleForTesting
    int h;
    private final Drawable[] i;

    public f(Drawable[] layers) {
        super(layers);
        h.b(layers.length > 0, "At least one layer required!");
        this.i = layers;
        this.d = new int[layers.length];
        this.e = new int[layers.length];
        this.f = 255;
        this.g = new boolean[layers.length];
        this.h = 0;
        this.a = 2;
        Arrays.fill(this.d, 0);
        this.d[0] = 255;
        Arrays.fill(this.e, 0);
        this.e[0] = 255;
        Arrays.fill(this.g, false);
        this.g[0] = true;
    }

    public final void invalidateSelf() {
        if (this.h == 0) {
            super.invalidateSelf();
        }
    }

    public final void b() {
        this.h++;
    }

    public final void c() {
        this.h--;
        invalidateSelf();
    }

    public final void c(int durationMs) {
        this.b = durationMs;
        if (this.a == 1) {
            this.a = 0;
        }
    }

    public final void d(int index) {
        this.a = 0;
        this.g[index] = true;
        invalidateSelf();
    }

    public final void e(int index) {
        this.a = 0;
        this.g[index] = false;
        invalidateSelf();
    }

    public final void d() {
        this.a = 0;
        Arrays.fill(this.g, true);
        invalidateSelf();
    }

    public final void e() {
        this.a = 2;
        for (int i = 0; i < this.i.length; i++) {
            this.e[i] = this.g[i] ? 255 : 0;
        }
        invalidateSelf();
    }

    private boolean a(float ratio) {
        boolean done = true;
        int i = 0;
        while (i < this.i.length) {
            this.e[i] = (int) (((float) this.d[i]) + (((float) ((this.g[i] ? 1 : -1) * 255)) * ratio));
            if (this.e[i] < 0) {
                this.e[i] = 0;
            }
            if (this.e[i] > 255) {
                this.e[i] = 255;
            }
            if (this.g[i] && this.e[i] < 255) {
                done = false;
            }
            if (!this.g[i] && this.e[i] > 0) {
                done = false;
            }
            i++;
        }
        return done;
    }

    public final void draw(Canvas canvas) {
        int i = 2;
        boolean z = false;
        boolean done = true;
        switch (this.a) {
            case 0:
                System.arraycopy(this.e, 0, this.d, 0, this.i.length);
                this.c = SystemClock.uptimeMillis();
                done = a(this.b == 0 ? 1.0f : 0.0f);
                if (!done) {
                    i = 1;
                }
                this.a = i;
                break;
            case 1:
                if (this.b > 0) {
                    z = true;
                }
                h.b(z);
                done = a(((float) (SystemClock.uptimeMillis() - this.c)) / ((float) this.b));
                if (!done) {
                    i = 1;
                }
                this.a = i;
                break;
            case 2:
                done = true;
                break;
        }
        for (int i2 = 0; i2 < this.i.length; i2++) {
            Drawable drawable = this.i[i2];
            int i3 = (this.e[i2] * this.f) / 255;
            if (drawable != null && i3 > 0) {
                this.h++;
                drawable.mutate().setAlpha(i3);
                this.h--;
                drawable.draw(canvas);
            }
        }
        if (!done) {
            invalidateSelf();
        }
    }

    public final void setAlpha(int alpha) {
        if (this.f != alpha) {
            this.f = alpha;
            invalidateSelf();
        }
    }

    public final int getAlpha() {
        return this.f;
    }
}
