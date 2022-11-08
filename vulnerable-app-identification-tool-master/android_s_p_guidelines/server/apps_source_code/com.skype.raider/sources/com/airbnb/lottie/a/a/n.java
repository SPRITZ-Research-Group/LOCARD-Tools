package com.airbnb.lottie.a.a;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.c.b.j;
import com.airbnb.lottie.c.b.q;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.c;
import java.util.List;

public final class n implements j, l, a {
    private final Path a = new Path();
    private final RectF b = new RectF();
    private final String c;
    private final LottieDrawable d;
    private final com.airbnb.lottie.a.b.a<?, PointF> e;
    private final com.airbnb.lottie.a.b.a<?, PointF> f;
    private final com.airbnb.lottie.a.b.a<?, Float> g;
    @Nullable
    private r h;
    private boolean i;

    public n(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, j rectShape) {
        this.c = rectShape.a();
        this.d = lottieDrawable;
        this.e = rectShape.d().a();
        this.f = rectShape.c().a();
        this.g = rectShape.b().a();
        layer.a(this.e);
        layer.a(this.f);
        layer.a(this.g);
        this.e.a((a) this);
        this.f.a((a) this);
        this.g.a((a) this);
    }

    public final String b() {
        return this.c;
    }

    public final void a(List<b> contentsBefore, List<b> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            b content = (b) contentsBefore.get(i);
            if ((content instanceof r) && ((r) content).c() == q.a.Simultaneously) {
                this.h = (r) content;
                this.h.a(this);
            }
        }
    }

    public final Path e() {
        if (this.i) {
            return this.a;
        }
        this.a.reset();
        PointF size = (PointF) this.f.e();
        float halfWidth = size.x / 2.0f;
        float halfHeight = size.y / 2.0f;
        float radius = this.g == null ? 0.0f : ((Float) this.g.e()).floatValue();
        float maxRadius = Math.min(halfWidth, halfHeight);
        if (radius > maxRadius) {
            radius = maxRadius;
        }
        PointF position = (PointF) this.e.e();
        this.a.moveTo(position.x + halfWidth, (position.y - halfHeight) + radius);
        this.a.lineTo(position.x + halfWidth, (position.y + halfHeight) - radius);
        if (radius > 0.0f) {
            this.b.set((position.x + halfWidth) - (2.0f * radius), (position.y + halfHeight) - (2.0f * radius), position.x + halfWidth, position.y + halfHeight);
            this.a.arcTo(this.b, 0.0f, 90.0f, false);
        }
        this.a.lineTo((position.x - halfWidth) + radius, position.y + halfHeight);
        if (radius > 0.0f) {
            this.b.set(position.x - halfWidth, (position.y + halfHeight) - (2.0f * radius), (position.x - halfWidth) + (2.0f * radius), position.y + halfHeight);
            this.a.arcTo(this.b, 90.0f, 90.0f, false);
        }
        this.a.lineTo(position.x - halfWidth, (position.y - halfHeight) + radius);
        if (radius > 0.0f) {
            this.b.set(position.x - halfWidth, position.y - halfHeight, (position.x - halfWidth) + (2.0f * radius), (position.y - halfHeight) + (2.0f * radius));
            this.a.arcTo(this.b, 180.0f, 90.0f, false);
        }
        this.a.lineTo((position.x + halfWidth) - radius, position.y - halfHeight);
        if (radius > 0.0f) {
            this.b.set((position.x + halfWidth) - (2.0f * radius), position.y - halfHeight, position.x + halfWidth, (position.y - halfHeight) + (2.0f * radius));
            this.a.arcTo(this.b, 270.0f, 90.0f, false);
        }
        this.a.close();
        f.a(this.a, this.h);
        this.i = true;
        return this.a;
    }

    public final void a(e keyPath, int depth, List<e> accumulator, e currentPartialKeyPath) {
        com.airbnb.lottie.e.e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public final <T> void a(T t, @Nullable c<T> cVar) {
    }

    public final void a() {
        this.i = false;
        this.d.invalidateSelf();
    }
}
