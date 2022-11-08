package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.k;

final class b implements a {
    private final long[] a;
    private final long[] b;
    private final long c;

    public static b a(k mpegAudioHeader, com.google.android.exoplayer2.d.k frame, long position, long inputLength) {
        frame.d(10);
        int numFrames = frame.n();
        if (numFrames <= 0) {
            return null;
        }
        int sampleRate = mpegAudioHeader.d;
        long durationUs = s.a((long) numFrames, ((long) (sampleRate >= 32000 ? 1152 : 576)) * 1000000, (long) sampleRate);
        int entryCount = frame.h();
        int scale = frame.h();
        int entrySize = frame.h();
        frame.d(2);
        position += (long) mpegAudioHeader.c;
        long[] timesUs = new long[(entryCount + 1)];
        long[] positions = new long[(entryCount + 1)];
        timesUs[0] = 0;
        positions[0] = position;
        for (int index = 1; index < timesUs.length; index++) {
            int segmentSize;
            long j;
            switch (entrySize) {
                case 1:
                    segmentSize = frame.g();
                    break;
                case 2:
                    segmentSize = frame.h();
                    break;
                case 3:
                    segmentSize = frame.k();
                    break;
                case 4:
                    segmentSize = frame.t();
                    break;
                default:
                    return null;
            }
            position += (long) (segmentSize * scale);
            timesUs[index] = (((long) index) * durationUs) / ((long) entryCount);
            if (inputLength == -1) {
                j = position;
            } else {
                j = Math.min(inputLength, position);
            }
            positions[index] = j;
        }
        return new b(timesUs, positions, durationUs);
    }

    private b(long[] timesUs, long[] positions, long durationUs) {
        this.a = timesUs;
        this.b = positions;
        this.c = durationUs;
    }

    public final boolean i_() {
        return true;
    }

    public final long a(long timeUs) {
        return this.b[s.a(this.a, timeUs, true)];
    }

    public final long b(long position) {
        return this.a[s.a(this.b, position, true)];
    }

    public final long b() {
        return this.c;
    }
}
