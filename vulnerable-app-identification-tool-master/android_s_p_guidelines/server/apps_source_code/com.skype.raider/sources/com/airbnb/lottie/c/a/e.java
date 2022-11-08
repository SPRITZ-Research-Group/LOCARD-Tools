package com.airbnb.lottie.c.a;

import android.graphics.PointF;
import com.airbnb.lottie.a.b.i;
import com.airbnb.lottie.a.b.j;
import com.airbnb.lottie.f.a;
import java.util.Collections;
import java.util.List;

public final class e implements m<PointF, PointF> {
    private final List<a<PointF>> a;

    public e() {
        this.a = Collections.singletonList(new a(new PointF(0.0f, 0.0f)));
    }

    public e(List<a<PointF>> keyframes) {
        this.a = keyframes;
    }

    public final com.airbnb.lottie.a.b.a<PointF, PointF> a() {
        if (((a) this.a.get(0)).d()) {
            return new j(this.a);
        }
        return new i(this.a);
    }
}
