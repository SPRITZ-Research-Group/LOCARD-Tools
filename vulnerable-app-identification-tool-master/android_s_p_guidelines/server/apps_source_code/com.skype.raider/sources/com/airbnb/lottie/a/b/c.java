package com.airbnb.lottie.a.b;

import com.airbnb.lottie.f.a;
import java.util.List;

public final class c extends f<Float> {
    public c(List<a<Float>> keyframes) {
        super(keyframes);
    }

    final /* synthetic */ Object a(a aVar, float f) {
        if (aVar.a == null || aVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        } else if (this.b != null) {
            return (Float) this.b.a(aVar.d, aVar.e.floatValue(), aVar.a, aVar.b, f, c(), f());
        } else {
            float floatValue = ((Float) aVar.a).floatValue();
            return Float.valueOf(((((Float) aVar.b).floatValue() - floatValue) * f) + floatValue);
        }
    }
}
