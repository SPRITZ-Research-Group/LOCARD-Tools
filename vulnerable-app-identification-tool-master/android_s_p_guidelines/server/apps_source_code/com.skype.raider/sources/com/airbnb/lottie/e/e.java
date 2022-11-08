package com.airbnb.lottie.e;

import android.graphics.PointF;
import com.airbnb.lottie.a.a.j;
import com.airbnb.lottie.c.f;
import java.util.List;

public final class e {
    public static PointF a(PointF p1, PointF p2) {
        return new PointF(p1.x + p2.x, p1.y + p2.y);
    }

    static int a(float x, float y) {
        int i;
        int i2 = (int) x;
        int i3 = (int) y;
        int i4 = i2 / i3;
        int i5 = i2 % i3;
        if (((i2 ^ i3) >= 0 ? 1 : null) != null || i5 == 0) {
            i = i4;
        } else {
            i = i4 - 1;
        }
        return i2 - (i * i3);
    }

    public static int a(int number) {
        return Math.max(0, Math.min(255, number));
    }

    public static float a(float number, float min, float max) {
        return Math.max(min, Math.min(max, number));
    }

    public static void a(com.airbnb.lottie.c.e keyPath, int depth, List<com.airbnb.lottie.c.e> accumulator, com.airbnb.lottie.c.e currentPartialKeyPath, j content) {
        if (keyPath.c(content.b(), depth)) {
            accumulator.add(currentPartialKeyPath.a(content.b()).a((f) content));
        }
    }
}
