package com.facebook.imagepipeline.c;

import com.facebook.imagepipeline.memory.ac;
import com.facebook.imagepipeline.memory.n;

public final class d {
    private final b a;
    private final n b;

    public d(ac poolFactory) {
        this.b = poolFactory.b();
        this.a = new b(poolFactory.d());
    }
}
