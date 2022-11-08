package com.google.android.exoplayer2.extractor.c;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.g;
import java.io.IOException;

final class c {

    private static final class a {
        public final int a;
        public final long b;

        private a(int id, long size) {
            this.a = id;
            this.b = size;
        }

        public static a a(g input, k scratch) throws IOException, InterruptedException {
            input.c(scratch.a, 0, 8);
            scratch.c(0);
            return new a(scratch.n(), scratch.m());
        }
    }

    public static b a(g input) throws IOException, InterruptedException {
        com.google.android.exoplayer2.d.a.a((Object) input);
        k scratch = new k(16);
        if (a.a(input, scratch).a != s.e("RIFF")) {
            return null;
        }
        input.c(scratch.a, 0, 4);
        scratch.c(0);
        if (scratch.n() != s.e("WAVE")) {
            return null;
        }
        a chunkHeader;
        while (true) {
            chunkHeader = a.a(input, scratch);
            if (chunkHeader.a == s.e("fmt ")) {
                break;
            }
            input.c((int) chunkHeader.b);
        }
        com.google.android.exoplayer2.d.a.b(chunkHeader.b >= 16);
        input.c(scratch.a, 0, 16);
        scratch.c(0);
        int type = scratch.i();
        int numChannels = scratch.i();
        int sampleRateHz = scratch.u();
        int averageBytesPerSecond = scratch.u();
        int blockAlignment = scratch.i();
        int bitsPerSample = scratch.i();
        int expectedBlockAlignment = (numChannels * bitsPerSample) / 8;
        if (blockAlignment != expectedBlockAlignment) {
            throw new com.google.android.exoplayer2.k("Expected block alignment: " + expectedBlockAlignment + "; got: " + blockAlignment);
        }
        int encoding = s.b(bitsPerSample);
        if (encoding == 0) {
            return null;
        }
        if (type != 1 && type != 65534) {
            return null;
        }
        input.c(((int) chunkHeader.b) - 16);
        return new b(numChannels, sampleRateHz, averageBytesPerSecond, blockAlignment, bitsPerSample, encoding);
    }
}
