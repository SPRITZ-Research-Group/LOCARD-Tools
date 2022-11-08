package com.airbnb.lottie.a.b;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.f.a;
import java.util.List;

public final class l extends a<com.airbnb.lottie.c.b.l, Path> {
    private final com.airbnb.lottie.c.b.l c = new com.airbnb.lottie.c.b.l();
    private final Path d = new Path();

    public l(List<a<com.airbnb.lottie.c.b.l>> keyframes) {
        super(keyframes);
    }

    public final /* synthetic */ Object a(a aVar, float f) {
        this.c.a((com.airbnb.lottie.c.b.l) aVar.a, (com.airbnb.lottie.c.b.l) aVar.b, f);
        com.airbnb.lottie.c.b.l lVar = this.c;
        Path path = this.d;
        path.reset();
        PointF a = lVar.a();
        path.moveTo(a.x, a.y);
        PointF pointF = new PointF(a.x, a.y);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= lVar.c().size()) {
                break;
            }
            com.airbnb.lottie.c.a aVar2 = (com.airbnb.lottie.c.a) lVar.c().get(i2);
            PointF a2 = aVar2.a();
            PointF b = aVar2.b();
            PointF c = aVar2.c();
            if (a2.equals(pointF) && b.equals(c)) {
                path.lineTo(c.x, c.y);
            } else {
                path.cubicTo(a2.x, a2.y, b.x, b.y, c.x, c.y);
            }
            pointF.set(c.x, c.y);
            i = i2 + 1;
        }
        if (lVar.b()) {
            path.close();
        }
        return this.d;
    }
}
