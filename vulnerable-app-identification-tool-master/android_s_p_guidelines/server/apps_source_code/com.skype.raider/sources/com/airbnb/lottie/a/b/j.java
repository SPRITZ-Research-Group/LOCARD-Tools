package com.airbnb.lottie.a.b;

import android.graphics.PointF;
import com.airbnb.lottie.f.a;
import java.util.List;

public final class j extends f<PointF> {
    private final PointF c = new PointF();

    public j(List<a<PointF>> keyframes) {
        super(keyframes);
    }

    public final /* synthetic */ Object a(a aVar, float f) {
        if (aVar.a == null || aVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF = (PointF) aVar.a;
        PointF pointF2 = (PointF) aVar.b;
        if (this.b != null) {
            return (PointF) this.b.a(aVar.d, aVar.e.floatValue(), pointF, pointF2, f, c(), f());
        }
        this.c.set(pointF.x + ((pointF2.x - pointF.x) * f), pointF.y + ((pointF2.y - pointF.y) * f));
        return this.c;
    }
}
