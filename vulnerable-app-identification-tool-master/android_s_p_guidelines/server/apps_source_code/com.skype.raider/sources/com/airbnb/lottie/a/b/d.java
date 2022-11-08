package com.airbnb.lottie.a.b;

import com.airbnb.lottie.c.b.c;
import com.airbnb.lottie.f.a;
import java.util.List;

public final class d extends f<c> {
    private final c c;

    public d(List<a<c>> keyframes) {
        int size = 0;
        super(keyframes);
        c startValue = ((a) keyframes.get(0)).a;
        if (startValue != null) {
            size = startValue.c();
        }
        this.c = new c(new float[size], new int[size]);
    }

    final /* bridge */ /* synthetic */ Object a(a aVar, float f) {
        this.c.a((c) aVar.a, (c) aVar.b, f);
        return this.c;
    }
}
