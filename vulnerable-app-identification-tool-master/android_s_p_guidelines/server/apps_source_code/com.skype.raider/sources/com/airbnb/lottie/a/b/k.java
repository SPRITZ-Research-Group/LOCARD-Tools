package com.airbnb.lottie.a.b;

import com.airbnb.lottie.f.a;
import com.airbnb.lottie.f.d;
import java.util.List;

public final class k extends f<d> {
    public k(List<a<d>> keyframes) {
        super(keyframes);
    }

    public final /* synthetic */ Object a(a aVar, float f) {
        if (aVar.a == null || aVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        d dVar = (d) aVar.a;
        d dVar2 = (d) aVar.b;
        if (this.b != null) {
            return (d) this.b.a(aVar.d, aVar.e.floatValue(), dVar, dVar2, f, c(), f());
        }
        float a = dVar.a();
        a += (dVar2.a() - a) * f;
        float b = dVar.b();
        return new d(a, b + ((dVar2.b() - b) * f));
    }
}
