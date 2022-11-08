package com.airbnb.lottie.a.a;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.c.b.q;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;
import java.util.List;

public final class e implements j, l, a {
    private final Path a = new Path();
    private final String b;
    private final LottieDrawable c;
    private final com.airbnb.lottie.a.b.a<?, PointF> d;
    private final com.airbnb.lottie.a.b.a<?, PointF> e;
    private final com.airbnb.lottie.c.b.a f;
    @Nullable
    private r g;
    private boolean h;

    public e(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, com.airbnb.lottie.c.b.a circleShape) {
        this.b = circleShape.a();
        this.c = lottieDrawable;
        this.d = circleShape.c().a();
        this.e = circleShape.b().a();
        this.f = circleShape;
        layer.a(this.d);
        layer.a(this.e);
        this.d.a((a) this);
        this.e.a((a) this);
    }

    public final void a(List<b> contentsBefore, List<b> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            b content = (b) contentsBefore.get(i);
            if ((content instanceof r) && ((r) content).c() == q.a.Simultaneously) {
                this.g = (r) content;
                this.g.a(this);
            }
        }
    }

    public final String b() {
        return this.b;
    }

    public final Path e() {
        if (this.h) {
            return this.a;
        }
        this.a.reset();
        PointF size = (PointF) this.d.e();
        float halfWidth = size.x / 2.0f;
        float halfHeight = size.y / 2.0f;
        float cpW = halfWidth * 0.55228f;
        float cpH = halfHeight * 0.55228f;
        this.a.reset();
        if (this.f.d()) {
            this.a.moveTo(0.0f, -halfHeight);
            this.a.cubicTo(0.0f - cpW, -halfHeight, -halfWidth, 0.0f - cpH, -halfWidth, 0.0f);
            this.a.cubicTo(-halfWidth, 0.0f + cpH, 0.0f - cpW, halfHeight, 0.0f, halfHeight);
            this.a.cubicTo(0.0f + cpW, halfHeight, halfWidth, 0.0f + cpH, halfWidth, 0.0f);
            this.a.cubicTo(halfWidth, 0.0f - cpH, 0.0f + cpW, -halfHeight, 0.0f, -halfHeight);
        } else {
            this.a.moveTo(0.0f, -halfHeight);
            this.a.cubicTo(0.0f + cpW, -halfHeight, halfWidth, 0.0f - cpH, halfWidth, 0.0f);
            this.a.cubicTo(halfWidth, 0.0f + cpH, 0.0f + cpW, halfHeight, 0.0f, halfHeight);
            this.a.cubicTo(0.0f - cpW, halfHeight, -halfWidth, 0.0f + cpH, -halfWidth, 0.0f);
            this.a.cubicTo(-halfWidth, 0.0f - cpH, 0.0f - cpW, -halfHeight, 0.0f, -halfHeight);
        }
        PointF position = (PointF) this.e.e();
        this.a.offset(position.x, position.y);
        this.a.close();
        f.a(this.a, this.g);
        this.h = true;
        return this.a;
    }

    public final void a(com.airbnb.lottie.c.e keyPath, int depth, List<com.airbnb.lottie.c.e> accumulator, com.airbnb.lottie.c.e currentPartialKeyPath) {
        com.airbnb.lottie.e.e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        if (property == g.g) {
            this.d.a((c) callback);
        } else if (property == g.h) {
            this.e.a((c) callback);
        }
    }

    public final void a() {
        this.h = false;
        this.c.invalidateSelf();
    }
}
