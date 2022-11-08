package com.google.android.exoplayer2.extractor.a;

import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.k;

abstract class d {
    protected final n a;

    public static final class a extends k {
        public a(String msg) {
            super(msg);
        }
    }

    protected abstract void a(com.google.android.exoplayer2.d.k kVar, long j) throws k;

    protected abstract boolean a(com.google.android.exoplayer2.d.k kVar) throws k;

    protected d(n output) {
        this.a = output;
    }

    public final void b(com.google.android.exoplayer2.d.k data, long timeUs) throws k {
        if (a(data)) {
            a(data, timeUs);
        }
    }
}
