package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class s implements p {
    private q a;
    private n b;
    private boolean c;

    public final void a(q timestampAdjuster, h extractorOutput, d idGenerator) {
        this.a = timestampAdjuster;
        idGenerator.a();
        this.b = extractorOutput.a(idGenerator.b());
        this.b.a(Format.a(idGenerator.c(), "application/x-scte35", null));
    }

    public final void a(k sectionData) {
        if (!this.c) {
            if (this.a.c() != -9223372036854775807L) {
                this.b.a(Format.a("application/x-scte35", this.a.c()));
                this.c = true;
            } else {
                return;
            }
        }
        int sampleSize = sectionData.b();
        this.b.a(sectionData, sampleSize);
        this.b.a(this.a.b(), 1, sampleSize, 0, null);
    }
}
