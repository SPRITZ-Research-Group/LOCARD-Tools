package com.airbnb.lottie.a.b;

import android.graphics.PointF;
import com.airbnb.lottie.a.b.a.a;
import java.util.Collections;

public final class m extends a<PointF, PointF> {
    private final PointF c = new PointF();
    private final a<Float, Float> d;
    private final a<Float, Float> e;

    public m(a<Float, Float> xAnimation, a<Float, Float> yAnimation) {
        super(Collections.emptyList());
        this.d = xAnimation;
        this.e = yAnimation;
        a(f());
    }

    public final void a(float progress) {
        this.d.a(progress);
        this.e.a(progress);
        this.c.set(((Float) this.d.e()).floatValue(), ((Float) this.e.e()).floatValue());
        for (int i = 0; i < this.a.size(); i++) {
            ((a) this.a.get(i)).a();
        }
    }

    final /* bridge */ /* synthetic */ Object a(com.airbnb.lottie.f.a aVar, float f) {
        return this.c;
    }

    public final /* bridge */ /* synthetic */ Object e() {
        return this.c;
    }
}
