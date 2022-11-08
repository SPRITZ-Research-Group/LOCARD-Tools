package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.n;
import java.io.IOException;

public final class c implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new c()};
        }
    };
    private h b;

    public final boolean a(g input) throws IOException, InterruptedException {
        try {
            e header = new e();
            if (!header.a(input, true) || (header.b & 2) != 2) {
                return false;
            }
            boolean z;
            int length = Math.min(header.i, 8);
            k scratch = new k(length);
            input.c(scratch.a, 0, length);
            scratch.c(0);
            if (scratch.b() >= 5 && scratch.g() == 127 && scratch.l() == 1179402563) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.b = new b();
            } else {
                scratch.c(0);
                if (j.b(scratch)) {
                    this.b = new j();
                } else {
                    scratch.c(0);
                    if (!g.b(scratch)) {
                        return false;
                    }
                    this.b = new g();
                }
            }
            return true;
        } catch (com.google.android.exoplayer2.k e) {
            return false;
        }
    }

    public final void a(h output) {
        n trackOutput = output.a(0);
        output.b();
        this.b.a(output, trackOutput);
    }

    public final void a(long position, long timeUs) {
        this.b.a(position, timeUs);
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        return this.b.a(input, seekPosition);
    }
}
