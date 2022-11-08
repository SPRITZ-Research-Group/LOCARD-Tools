package com.airbnb.lottie.a.b;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.f.a;
import java.util.List;

public final class i extends f<PointF> {
    private final PointF c = new PointF();
    private final float[] d = new float[2];
    private h e;
    private PathMeasure f;

    public final /* synthetic */ Object a(a aVar, float f) {
        h hVar = (h) aVar;
        Path a = hVar.a();
        if (a == null) {
            return (PointF) aVar.a;
        }
        if (this.b != null) {
            return (PointF) this.b.a(hVar.d, hVar.e.floatValue(), hVar.a, hVar.b, c(), f, f());
        }
        if (this.e != hVar) {
            this.f = new PathMeasure(a, false);
            this.e = hVar;
        }
        this.f.getPosTan(this.f.getLength() * f, this.d, null);
        this.c.set(this.d[0], this.d[1]);
        return this.c;
    }

    public i(List<? extends a<PointF>> keyframes) {
        super(keyframes);
    }
}
