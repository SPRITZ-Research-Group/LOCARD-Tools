package com.google.android.exoplayer2;

public final class o {
    public static final o a = new o(0);
    public final int b;

    public o(int tunnelingAudioSessionId) {
        this.b = tunnelingAudioSessionId;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.b != ((o) obj).b) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.b;
    }
}
