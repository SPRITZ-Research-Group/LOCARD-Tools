package com.facebook.drawee.d;

import android.support.annotation.ColorInt;
import com.facebook.common.internal.h;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class e {
    private a a = a.BITMAP_ONLY;
    private boolean b = false;
    private float[] c = null;
    private int d = 0;
    private float e = 0.0f;
    private int f = 0;
    private float g = 0.0f;
    private boolean h = false;

    public enum a {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    public final e a(boolean roundAsCircle) {
        this.b = roundAsCircle;
        return this;
    }

    public final boolean a() {
        return this.b;
    }

    public final e a(float radius) {
        Arrays.fill(j(), radius);
        return this;
    }

    public final e a(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        float[] radii = j();
        radii[1] = topLeft;
        radii[0] = topLeft;
        radii[3] = topRight;
        radii[2] = topRight;
        radii[5] = bottomRight;
        radii[4] = bottomRight;
        radii[7] = bottomLeft;
        radii[6] = bottomLeft;
        return this;
    }

    public final float[] b() {
        return this.c;
    }

    public final e a(a roundingMethod) {
        this.a = roundingMethod;
        return this;
    }

    public final a c() {
        return this.a;
    }

    public final e a(@ColorInt int overlayColor) {
        this.d = overlayColor;
        this.a = a.OVERLAY_COLOR;
        return this;
    }

    public final int d() {
        return this.d;
    }

    private float[] j() {
        if (this.c == null) {
            this.c = new float[8];
        }
        return this.c;
    }

    public static e e() {
        return new e().a(0.0f);
    }

    public final e b(float width) {
        h.a(width >= 0.0f, (Object) "the border width cannot be < 0");
        this.e = width;
        return this;
    }

    public final float f() {
        return this.e;
    }

    public final e b(@ColorInt int color) {
        this.f = color;
        return this;
    }

    public final int g() {
        return this.f;
    }

    public final e a(@ColorInt int color, float width) {
        h.a(width >= 0.0f, (Object) "the border width cannot be < 0");
        this.e = width;
        this.f = color;
        return this;
    }

    public final e c(float padding) {
        h.a(padding >= 0.0f, (Object) "the padding cannot be < 0");
        this.g = padding;
        return this;
    }

    public final float h() {
        return this.g;
    }

    public final boolean i() {
        return this.h;
    }

    public final boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        e that = (e) o;
        if (this.b == that.b && this.d == that.d && Float.compare(that.e, this.e) == 0 && this.f == that.f && Float.compare(that.g, this.g) == 0 && this.a == that.a && this.h == that.h) {
            return Arrays.equals(this.c, that.c);
        }
        return false;
    }

    public final int hashCode() {
        int result;
        int i;
        int i2 = 1;
        if (this.a != null) {
            result = this.a.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        i3 = (i3 + i) * 31;
        if (this.c != null) {
            i = Arrays.hashCode(this.c);
        } else {
            i = 0;
        }
        i3 = (((i3 + i) * 31) + this.d) * 31;
        if (this.e != 0.0f) {
            i = Float.floatToIntBits(this.e);
        } else {
            i = 0;
        }
        i3 = (((i3 + i) * 31) + this.f) * 31;
        if (this.g != 0.0f) {
            i = Float.floatToIntBits(this.g);
        } else {
            i = 0;
        }
        i = (i3 + i) * 31;
        if (!this.h) {
            i2 = 0;
        }
        return i + i2;
    }
}
