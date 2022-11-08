package com.airbnb.lottie.a.b;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.a;

public final class h extends a<PointF> {
    @Nullable
    private Path h;

    public h(e composition, a<PointF> keyframe) {
        super(composition, keyframe.a, keyframe.b, keyframe.c, keyframe.d, keyframe.e);
        boolean equals = (this.b == null || this.a == null || !((PointF) this.a).equals(((PointF) this.b).x, ((PointF) this.b).y)) ? false : true;
        if (this.b != null && !equals) {
            this.h = f.a((PointF) this.a, (PointF) this.b, keyframe.f, keyframe.g);
        }
    }

    @Nullable
    final Path a() {
        return this.h;
    }
}
