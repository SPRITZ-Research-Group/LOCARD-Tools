package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.ah;
import com.google.android.exoplayer2.h;
import com.google.android.exoplayer2.source.TrackGroupArray;
import defpackage.bbi;
import defpackage.bcz;

public abstract class m {
    private n a;
    private bbi b;

    public abstract o a(ah[] ahVarArr, TrackGroupArray trackGroupArray) throws h;

    public abstract void a(Object obj);

    public final void a(n nVar, bbi bbi) {
        this.a = nVar;
        this.b = bbi;
    }

    protected final bbi a() {
        return (bbi) bcz.a(this.b);
    }
}
