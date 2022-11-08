package com.google.android.exoplayer2;

public final class l {
    public static final l a = new l(1.0f, 1.0f);
    public final float b;
    public final float c;
    private final int d;

    public l(float speed, float pitch) {
        this.b = speed;
        this.c = pitch;
        this.d = Math.round(1000.0f * speed);
    }

    public final long a(long timeMs) {
        return ((long) this.d) * timeMs;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        l other = (l) obj;
        if (this.b == other.b && this.c == other.c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((Float.floatToRawIntBits(this.b) + 527) * 31) + Float.floatToRawIntBits(this.c);
    }
}
