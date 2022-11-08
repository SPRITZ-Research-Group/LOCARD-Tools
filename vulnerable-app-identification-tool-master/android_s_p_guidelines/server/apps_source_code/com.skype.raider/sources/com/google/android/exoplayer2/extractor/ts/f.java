package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.a;
import com.google.android.exoplayer2.extractor.ts.t.d;
import java.util.Collections;
import java.util.List;

public final class f implements g {
    private final List<a> a;
    private final n[] b;
    private boolean c;
    private int d;
    private int e;
    private long f;

    public f(List<a> subtitleInfos) {
        this.a = subtitleInfos;
        this.b = new n[subtitleInfos.size()];
    }

    public final void a() {
        this.c = false;
    }

    public final void a(h extractorOutput, d idGenerator) {
        for (int i = 0; i < this.b.length; i++) {
            a subtitleInfo = (a) this.a.get(i);
            idGenerator.a();
            n output = extractorOutput.a(idGenerator.b());
            output.a(Format.a(idGenerator.c(), "application/dvbsubs", Collections.singletonList(subtitleInfo.c), subtitleInfo.a, null));
            this.b[i] = output;
        }
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        if (dataAlignmentIndicator) {
            this.c = true;
            this.f = pesTimeUs;
            this.e = 0;
            this.d = 2;
        }
    }

    public final void b() {
        if (this.c) {
            for (n a : this.b) {
                a.a(this.f, 1, this.e, 0, null);
            }
            this.c = false;
        }
    }

    public final void a(k data) {
        int i = 0;
        if (!this.c) {
            return;
        }
        if (this.d == 2 && !a(data, 32)) {
            return;
        }
        if (this.d != 1 || a(data, 0)) {
            int dataPosition = data.d();
            int bytesAvailable = data.b();
            n[] nVarArr = this.b;
            int length = nVarArr.length;
            while (i < length) {
                n output = nVarArr[i];
                data.c(dataPosition);
                output.a(data, bytesAvailable);
                i++;
            }
            this.e += bytesAvailable;
        }
    }

    private boolean a(k data, int expectedValue) {
        if (data.b() == 0) {
            return false;
        }
        if (data.g() != expectedValue) {
            this.c = false;
        }
        this.d--;
        return this.c;
    }
}
