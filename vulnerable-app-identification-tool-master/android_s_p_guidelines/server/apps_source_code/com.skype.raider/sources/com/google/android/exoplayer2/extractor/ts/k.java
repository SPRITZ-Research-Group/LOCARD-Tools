package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class k implements g {
    private final com.google.android.exoplayer2.d.k a = new com.google.android.exoplayer2.d.k(10);
    private n b;
    private boolean c;
    private long d;
    private int e;
    private int f;

    public final void a() {
        this.c = false;
    }

    public final void a(h extractorOutput, d idGenerator) {
        idGenerator.a();
        this.b = extractorOutput.a(idGenerator.b());
        this.b.a(Format.a(idGenerator.c(), "application/id3", null));
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        if (dataAlignmentIndicator) {
            this.c = true;
            this.d = pesTimeUs;
            this.e = 0;
            this.f = 0;
        }
    }

    public final void a(com.google.android.exoplayer2.d.k data) {
        if (this.c) {
            int bytesAvailable = data.b();
            if (this.f < 10) {
                int headerBytesAvailable = Math.min(bytesAvailable, 10 - this.f);
                System.arraycopy(data.a, data.d(), this.a.a, this.f, headerBytesAvailable);
                if (this.f + headerBytesAvailable == 10) {
                    this.a.c(0);
                    if (73 == this.a.g() && 68 == this.a.g() && 51 == this.a.g()) {
                        this.a.d(3);
                        this.e = this.a.s() + 10;
                    } else {
                        this.c = false;
                        return;
                    }
                }
            }
            int bytesToWrite = Math.min(bytesAvailable, this.e - this.f);
            this.b.a(data, bytesToWrite);
            this.f += bytesToWrite;
        }
    }

    public final void b() {
        if (this.c && this.e != 0 && this.f == this.e) {
            this.b.a(this.d, 1, this.e, 0, null);
            this.c = false;
        }
    }
}
