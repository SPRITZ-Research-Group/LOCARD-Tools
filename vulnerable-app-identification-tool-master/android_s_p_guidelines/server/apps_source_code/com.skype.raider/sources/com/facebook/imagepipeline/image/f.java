package com.facebook.imagepipeline.image;

public final class f implements g {
    public static final g a = a(Integer.MAX_VALUE, true, true);
    int b;
    boolean c;
    boolean d;

    private f(int quality, boolean isOfGoodEnoughQuality, boolean isOfFullQuality) {
        this.b = quality;
        this.c = isOfGoodEnoughQuality;
        this.d = isOfFullQuality;
    }

    public final int a() {
        return this.b;
    }

    public final boolean b() {
        return this.c;
    }

    public final boolean c() {
        return this.d;
    }

    public final int hashCode() {
        int i = 0;
        int i2 = (this.c ? 4194304 : 0) ^ this.b;
        if (this.d) {
            i = 8388608;
        }
        return i2 ^ i;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof f)) {
            return false;
        }
        f that = (f) other;
        if (this.b == that.b && this.c == that.c && this.d == that.d) {
            return true;
        }
        return false;
    }

    public static g a(int quality, boolean isOfGoodEnoughQuality, boolean isOfFullQuality) {
        return new f(quality, isOfGoodEnoughQuality, isOfFullQuality);
    }
}
