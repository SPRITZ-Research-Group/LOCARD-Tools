package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.k;

final class c implements a {
    private final long a;
    private final long b;
    private final long c;
    private final long[] d;
    private final long e;
    private final int f;

    public static c a(k mpegAudioHeader, com.google.android.exoplayer2.d.k frame, long position, long inputLength) {
        int samplesPerFrame = mpegAudioHeader.g;
        int sampleRate = mpegAudioHeader.d;
        long firstFramePosition = position + ((long) mpegAudioHeader.c);
        int flags = frame.n();
        if ((flags & 1) == 1) {
            int frameCount = frame.t();
            if (frameCount != 0) {
                long durationUs = s.a((long) frameCount, ((long) samplesPerFrame) * 1000000, (long) sampleRate);
                if ((flags & 6) != 6) {
                    return new c(firstFramePosition, durationUs, inputLength);
                }
                long sizeBytes = (long) frame.t();
                frame.d(1);
                long[] tableOfContents = new long[99];
                for (int i = 0; i < 99; i++) {
                    tableOfContents[i] = (long) frame.g();
                }
                return new c(firstFramePosition, durationUs, inputLength, tableOfContents, sizeBytes, mpegAudioHeader.c);
            }
        }
        return null;
    }

    private c(long firstFramePosition, long durationUs, long inputLength) {
        this(firstFramePosition, durationUs, inputLength, null, 0, 0);
    }

    private c(long firstFramePosition, long durationUs, long inputLength, long[] tableOfContents, long sizeBytes, int headerSize) {
        this.a = firstFramePosition;
        this.b = durationUs;
        this.c = inputLength;
        this.d = tableOfContents;
        this.e = sizeBytes;
        this.f = headerSize;
    }

    public final boolean i_() {
        return this.d != null;
    }

    public final long a(long timeUs) {
        if (!i_()) {
            return this.a;
        }
        float fx;
        float percent = (((float) timeUs) * 100.0f) / ((float) this.b);
        if (percent <= 0.0f) {
            fx = 0.0f;
        } else if (percent >= 100.0f) {
            fx = 256.0f;
        } else {
            float fa;
            float fb;
            int a = (int) percent;
            if (a == 0) {
                fa = 0.0f;
            } else {
                fa = (float) this.d[a - 1];
            }
            if (a < 99) {
                fb = (float) this.d[a];
            } else {
                fb = 256.0f;
            }
            fx = fa + ((fb - fa) * (percent - ((float) a)));
        }
        return Math.min(Math.round((0.00390625d * ((double) fx)) * ((double) this.e)) + this.a, this.c != -1 ? this.c - 1 : ((this.a - ((long) this.f)) + this.e) - 1);
    }

    public final long b(long position) {
        if (!i_() || position < this.a) {
            return 0;
        }
        double offsetByte = (256.0d * ((double) (position - this.a))) / ((double) this.e);
        int previousTocPosition = s.a(this.d, (long) offsetByte, false) + 1;
        long previousTime = a(previousTocPosition);
        long previousByte = previousTocPosition == 0 ? 0 : this.d[previousTocPosition - 1];
        long nextByte = previousTocPosition == 99 ? 256 : this.d[previousTocPosition];
        return previousTime + (nextByte == previousByte ? 0 : (long) ((((double) (a(previousTocPosition + 1) - previousTime)) * (offsetByte - ((double) previousByte))) / ((double) (nextByte - previousByte))));
    }

    public final long b() {
        return this.b;
    }

    private long a(int tocPosition) {
        return (this.b * ((long) tocPosition)) / 100;
    }
}
