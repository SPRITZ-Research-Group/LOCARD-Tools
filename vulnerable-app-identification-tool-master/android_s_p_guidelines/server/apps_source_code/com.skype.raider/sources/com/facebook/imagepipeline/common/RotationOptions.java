package com.facebook.imagepipeline.common;

import com.facebook.common.i.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RotationOptions {
    private static final RotationOptions c = new RotationOptions(-1, false);
    private static final RotationOptions d = new RotationOptions(-2, false);
    private static final RotationOptions e = new RotationOptions(-1, true);
    private final int a;
    private final boolean b;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RotationAngle {
    }

    public static RotationOptions a() {
        return c;
    }

    public static RotationOptions b() {
        return e;
    }

    public static RotationOptions a(int angle) {
        return new RotationOptions(angle, false);
    }

    private RotationOptions(int rotation, boolean canDeferUntilRendered) {
        this.a = rotation;
        this.b = canDeferUntilRendered;
    }

    public final boolean c() {
        return this.a == -1;
    }

    public final boolean d() {
        return this.a != -2;
    }

    public final int e() {
        if (!c()) {
            return this.a;
        }
        throw new IllegalStateException("Rotation is set to use EXIF");
    }

    public final boolean f() {
        return this.b;
    }

    public final int hashCode() {
        int i;
        int i2 = 0;
        Integer valueOf = Integer.valueOf(this.a);
        Boolean valueOf2 = Boolean.valueOf(this.b);
        if (valueOf == null) {
            i = 0;
        } else {
            i = valueOf.hashCode();
        }
        if (valueOf2 != null) {
            i2 = valueOf2.hashCode();
        }
        return b.a(i, i2);
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RotationOptions)) {
            return false;
        }
        RotationOptions that = (RotationOptions) other;
        if (this.a == that.a && this.b == that.b) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return String.format(null, "%d defer:%b", new Object[]{Integer.valueOf(this.a), Boolean.valueOf(this.b)});
    }
}
