package com.airbnb.lottie.a.b;

import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f.c;
import java.util.ArrayList;
import java.util.List;

public abstract class a<K, A> {
    final List<a> a = new ArrayList();
    @Nullable
    protected c<A> b;
    private boolean c = false;
    private final List<? extends com.airbnb.lottie.f.a<K>> d;
    private float e = 0.0f;
    @Nullable
    private com.airbnb.lottie.f.a<K> f;

    public interface a {
        void a();
    }

    abstract A a(com.airbnb.lottie.f.a<K> aVar, float f);

    a(List<? extends com.airbnb.lottie.f.a<K>> keyframes) {
        this.d = keyframes;
    }

    public final void a() {
        this.c = true;
    }

    public final void a(a listener) {
        this.a.add(listener);
    }

    public void a(@FloatRange(from = 0.0d, to = 1.0d) float progress) {
        if (progress < h()) {
            progress = h();
        } else if (progress > d()) {
            progress = d();
        }
        if (progress != this.e) {
            this.e = progress;
            b();
        }
    }

    public void b() {
        for (int i = 0; i < this.a.size(); i++) {
            ((a) this.a.get(i)).a();
        }
    }

    private com.airbnb.lottie.f.a<K> g() {
        if (this.f != null && this.f.a(this.e)) {
            return this.f;
        }
        com.airbnb.lottie.f.a<K> keyframe = (com.airbnb.lottie.f.a) this.d.get(this.d.size() - 1);
        if (this.e < keyframe.b()) {
            for (int i = this.d.size() - 1; i >= 0; i--) {
                keyframe = (com.airbnb.lottie.f.a) this.d.get(i);
                if (keyframe.a(this.e)) {
                    break;
                }
            }
        }
        this.f = keyframe;
        return keyframe;
    }

    final float c() {
        if (this.c) {
            return 0.0f;
        }
        com.airbnb.lottie.f.a<K> keyframe = g();
        if (keyframe.d()) {
            return 0.0f;
        }
        return (this.e - keyframe.b()) / (keyframe.c() - keyframe.b());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    private float h() {
        return this.d.isEmpty() ? 0.0f : ((com.airbnb.lottie.f.a) this.d.get(0)).b();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    float d() {
        return this.d.isEmpty() ? 1.0f : ((com.airbnb.lottie.f.a) this.d.get(this.d.size() - 1)).c();
    }

    public A e() {
        float f;
        com.airbnb.lottie.f.a g = g();
        com.airbnb.lottie.f.a g2 = g();
        if (g2.d()) {
            f = 0.0f;
        } else {
            f = g2.c.getInterpolation(c());
        }
        return a(g, f);
    }

    public final float f() {
        return this.e;
    }

    public final void a(@Nullable c<A> valueCallback) {
        if (this.b != null) {
            this.b.a(null);
        }
        this.b = valueCallback;
        if (valueCallback != null) {
            valueCallback.a(this);
        }
    }
}
