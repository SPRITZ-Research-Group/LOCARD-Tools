package com.airbnb.lottie.a.b;

import com.airbnb.lottie.f.a;
import java.util.List;

public final class e extends f<Integer> {
    public e(List<a<Integer>> keyframes) {
        super(keyframes);
    }

    final /* synthetic */ Object a(a aVar, float f) {
        if (aVar.a == null || aVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        } else if (this.b != null) {
            return (Integer) this.b.a(aVar.d, aVar.e.floatValue(), aVar.a, aVar.b, f, c(), f());
        } else {
            int intValue = ((Integer) aVar.a).intValue();
            return Integer.valueOf((int) ((((float) (((Integer) aVar.b).intValue() - intValue)) * f) + ((float) intValue)));
        }
    }
}
