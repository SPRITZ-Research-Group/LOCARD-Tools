package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.ts.t.d;
import java.io.IOException;

public final class a implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new a()};
        }
    };
    private static final int b = s.e("ID3");
    private final long c;
    private final k d;
    private b e;
    private boolean f;

    public a() {
        this((byte) 0);
    }

    private a(byte b) {
        this.c = 0;
        this.d = new k(2786);
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        k scratch = new k(10);
        int startPosition = 0;
        while (true) {
            input.c(scratch.a, 0, 10);
            scratch.c(0);
            if (scratch.k() != b) {
                break;
            }
            scratch.d(3);
            int length = scratch.s();
            startPosition += length + 10;
            input.c(length);
        }
        input.a();
        input.c(startPosition);
        int headerPosition = startPosition;
        int validFramesCount = 0;
        while (true) {
            input.c(scratch.a, 0, 5);
            scratch.c(0);
            if (scratch.h() != 2935) {
                validFramesCount = 0;
                input.a();
                headerPosition++;
                if (headerPosition - startPosition >= 8192) {
                    return false;
                }
                input.c(headerPosition);
            } else {
                validFramesCount++;
                if (validFramesCount >= 4) {
                    return true;
                }
                int frameSize = com.google.android.exoplayer2.a.a.a(scratch.a);
                if (frameSize == -1) {
                    return false;
                }
                input.c(frameSize - 5);
            }
        }
    }

    public final void a(h output) {
        this.e = new b();
        this.e.a(output, new d(0, 1));
        output.b();
        output.a(new com.google.android.exoplayer2.extractor.m.a(-9223372036854775807L));
    }

    public final void a(long position, long timeUs) {
        this.f = false;
        this.e.a();
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        int bytesRead = input.a(this.d.a, 0, 2786);
        if (bytesRead == -1) {
            return -1;
        }
        this.d.c(0);
        this.d.b(bytesRead);
        if (!this.f) {
            this.e.a(this.c, true);
            this.f = true;
        }
        this.e.a(this.d);
        return 0;
    }
}
