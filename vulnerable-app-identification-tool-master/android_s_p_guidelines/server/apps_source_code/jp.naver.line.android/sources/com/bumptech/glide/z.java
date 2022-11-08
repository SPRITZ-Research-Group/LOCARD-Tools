package com.bumptech.glide;

import defpackage.akw;
import defpackage.ala;
import defpackage.alt;

public abstract class z<CHILD extends z<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    private ala<? super TranscodeType> a = akw.a();

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    public final CHILD b() {
        try {
            return (z) super.clone();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    final ala<? super TranscodeType> c() {
        return this.a;
    }

    public final CHILD a(ala<? super TranscodeType> ala) {
        this.a = (ala) alt.a((Object) ala, "Argument must not be null");
        return this;
    }
}
