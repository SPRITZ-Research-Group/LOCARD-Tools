package com.airbnb.lottie.a.a;

import android.graphics.Path;
import android.graphics.Path.FillType;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.c.b.o;
import com.airbnb.lottie.c.b.q;
import com.airbnb.lottie.e.f;
import java.util.List;

public final class p implements l, a {
    private final Path a = new Path();
    private final String b;
    private final LottieDrawable c;
    private final com.airbnb.lottie.a.b.a<?, Path> d;
    private boolean e;
    @Nullable
    private r f;

    public p(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, o shape) {
        this.b = shape.a();
        this.c = lottieDrawable;
        this.d = shape.b().a();
        layer.a(this.d);
        this.d.a((a) this);
    }

    public final void a(List<b> contentsBefore, List<b> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            b content = (b) contentsBefore.get(i);
            if ((content instanceof r) && ((r) content).c() == q.a.Simultaneously) {
                this.f = (r) content;
                this.f.a(this);
            }
        }
    }

    public final Path e() {
        if (this.e) {
            return this.a;
        }
        this.a.reset();
        this.a.set((Path) this.d.e());
        this.a.setFillType(FillType.EVEN_ODD);
        f.a(this.a, this.f);
        this.e = true;
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final void a() {
        this.e = false;
        this.c.invalidateSelf();
    }
}
