package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.d.s;

final class d {

    public static final class a {
        public final long[] a;
        public final int[] b;
        public final int c;
        public final long[] d;
        public final int[] e;

        /* synthetic */ a(long[] x0, int[] x1, int x2, long[] x3, int[] x4, byte b) {
            this(x0, x1, x2, x3, x4);
        }

        private a(long[] offsets, int[] sizes, int maximumSize, long[] timestamps, int[] flags) {
            this.a = offsets;
            this.b = sizes;
            this.c = maximumSize;
            this.d = timestamps;
            this.e = flags;
        }
    }

    public static a a(int fixedSampleSize, long[] chunkOffsets, int[] chunkSampleCounts, long timestampDeltaInTimeUnits) {
        int maxSampleCount = 8192 / fixedSampleSize;
        int rechunkedSampleCount = 0;
        for (int chunkSampleCount : chunkSampleCounts) {
            rechunkedSampleCount += s.a(chunkSampleCount, maxSampleCount);
        }
        long[] offsets = new long[rechunkedSampleCount];
        int[] sizes = new int[rechunkedSampleCount];
        int maximumSize = 0;
        long[] timestamps = new long[rechunkedSampleCount];
        int[] flags = new int[rechunkedSampleCount];
        int originalSampleIndex = 0;
        int newSampleIndex = 0;
        for (int chunkIndex = 0; chunkIndex < chunkSampleCounts.length; chunkIndex++) {
            int chunkSamplesRemaining = chunkSampleCounts[chunkIndex];
            long sampleOffset = chunkOffsets[chunkIndex];
            while (chunkSamplesRemaining > 0) {
                int bufferSampleCount = Math.min(maxSampleCount, chunkSamplesRemaining);
                offsets[newSampleIndex] = sampleOffset;
                sizes[newSampleIndex] = fixedSampleSize * bufferSampleCount;
                maximumSize = Math.max(maximumSize, sizes[newSampleIndex]);
                timestamps[newSampleIndex] = ((long) originalSampleIndex) * timestampDeltaInTimeUnits;
                flags[newSampleIndex] = 1;
                sampleOffset += (long) sizes[newSampleIndex];
                originalSampleIndex += bufferSampleCount;
                chunkSamplesRemaining -= bufferSampleCount;
                newSampleIndex++;
            }
        }
        return new a(offsets, sizes, maximumSize, timestamps, flags, (byte) 0);
    }
}
