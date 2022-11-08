package com.google.android.exoplayer2;

public final class ai {
    public static final ai a = new ai(0);
    public final int b;

    public ai(int i) {
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b == ((ai) obj).b;
    }

    public final int hashCode() {
        return this.b;
    }
}
