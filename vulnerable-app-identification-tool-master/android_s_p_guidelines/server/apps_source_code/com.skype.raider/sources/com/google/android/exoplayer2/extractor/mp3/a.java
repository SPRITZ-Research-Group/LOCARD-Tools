package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.d.s;

final class a implements a {
    private final long a;
    private final int b;
    private final long c;

    public a(long firstFramePosition, int bitrate, long inputLength) {
        this.a = firstFramePosition;
        this.b = bitrate;
        this.c = inputLength == -1 ? -9223372036854775807L : b(inputLength);
    }

    public final boolean i_() {
        return this.c != -9223372036854775807L;
    }

    public final long a(long timeUs) {
        if (this.c == -9223372036854775807L) {
            return 0;
        }
        return this.a + ((((long) this.b) * s.a(timeUs, this.c)) / 8000000);
    }

    public final long b(long position) {
        return ((Math.max(0, position - this.a) * 1000000) * 8) / ((long) this.b);
    }

    public final long b() {
        return this.c;
    }
}
