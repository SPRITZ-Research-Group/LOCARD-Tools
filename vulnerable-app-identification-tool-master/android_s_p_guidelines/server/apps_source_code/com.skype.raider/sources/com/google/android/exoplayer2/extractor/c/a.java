package com.google.android.exoplayer2.extractor.c;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.k;
import java.io.IOException;

public final class a implements f, m {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new a()};
        }
    };
    private h b;
    private n c;
    private b d;
    private int e;
    private int f;

    public final boolean a(g input) throws IOException, InterruptedException {
        return c.a(input) != null;
    }

    public final void a(h output) {
        this.b = output;
        this.c = output.a(0);
        this.d = null;
        output.b();
    }

    public final void a(long position, long timeUs) {
        this.f = 0;
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        if (this.d == null) {
            this.d = c.a(input);
            if (this.d == null) {
                throw new k("Unsupported or unrecognized wav header.");
            }
            this.c.a(Format.a(null, "audio/raw", this.d.c(), 32768, this.d.e(), this.d.d(), this.d.g(), null, null, 0, null));
            this.e = this.d.b();
        }
        if (!this.d.f()) {
            Object obj = this.d;
            com.google.android.exoplayer2.d.a.a((Object) input);
            com.google.android.exoplayer2.d.a.a(obj);
            input.a();
            com.google.android.exoplayer2.d.k kVar = new com.google.android.exoplayer2.d.k(8);
            a a = a.a(input, kVar);
            while (a.a != s.e("data")) {
                new StringBuilder("Ignoring unknown WAV chunk: ").append(a.a);
                long j = 8 + a.b;
                if (a.a == s.e("RIFF")) {
                    j = 12;
                }
                if (j > 2147483647L) {
                    throw new k("Chunk is too large (~2GB+) to skip; id: " + a.a);
                }
                input.b((int) j);
                a = a.a(input, kVar);
            }
            input.b(8);
            obj.a(input.c(), a.b);
            this.b.a((m) this);
        }
        int bytesAppended = this.c.a(input, 32768 - this.f, true);
        if (bytesAppended != -1) {
            this.f += bytesAppended;
        }
        int pendingFrames = this.f / this.e;
        if (pendingFrames > 0) {
            long timeUs = this.d.b(input.c() - ((long) this.f));
            int size = pendingFrames * this.e;
            this.f -= size;
            this.c.a(timeUs, 1, size, this.f, null);
        }
        return bytesAppended == -1 ? -1 : 0;
    }

    public final long b() {
        return this.d.a();
    }

    public final boolean i_() {
        return true;
    }

    public final long a(long timeUs) {
        return this.d.a(timeUs);
    }
}
