package com.airbnb.lottie.a.b;

import com.airbnb.lottie.f.a;
import java.util.List;

public final class b extends f<Integer> {
    public b(List<a<Integer>> keyframes) {
        super(keyframes);
    }

    public final /* synthetic */ Object a(a aVar, float f) {
        if (aVar.a == null || aVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        int intValue = ((Integer) aVar.a).intValue();
        int intValue2 = ((Integer) aVar.b).intValue();
        if (this.b == null) {
            return Integer.valueOf(com.airbnb.lottie.e.b.a(f, intValue, intValue2));
        }
        return (Integer) this.b.a(aVar.d, aVar.e.floatValue(), Integer.valueOf(intValue), Integer.valueOf(intValue2), f, c(), f());
    }
}
