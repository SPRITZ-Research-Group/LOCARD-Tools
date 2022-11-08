package com.google.android.exoplayer2.c;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.n;
import com.google.android.exoplayer2.source.k;

public abstract class h {
    private a a;

    public interface a {
    }

    public abstract i a(n[] nVarArr, k kVar) throws ExoPlaybackException;

    public abstract void a(Object obj);

    public final void a(a listener) {
        this.a = listener;
    }
}
