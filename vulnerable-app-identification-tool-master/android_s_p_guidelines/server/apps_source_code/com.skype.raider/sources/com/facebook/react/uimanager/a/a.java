package com.facebook.react.uimanager.a;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.f;
import java.util.Map;
import javax.annotation.Nullable;

abstract class a {
    private static final Map<e, Interpolator> c = e.a(e.LINEAR, new LinearInterpolator(), e.EASE_IN, new AccelerateInterpolator(), e.EASE_OUT, new DecelerateInterpolator(), e.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator(), e.SPRING, new n());
    @Nullable
    protected b a;
    protected int b;
    @Nullable
    private Interpolator d;
    private int e;

    @Nullable
    abstract Animation a(View view, int i, int i2, int i3, int i4);

    abstract boolean a();

    a() {
    }

    public final void b() {
        this.a = null;
        this.b = 0;
        this.e = 0;
        this.d = null;
    }

    public final void a(am data, int globalDuration) {
        this.a = data.hasKey("property") ? b.a(data.getString("property")) : null;
        if (data.hasKey("duration")) {
            globalDuration = data.getInt("duration");
        }
        this.b = globalDuration;
        this.e = data.hasKey("delay") ? data.getInt("delay") : 0;
        if (data.hasKey("type")) {
            e a = e.a(data.getString("type"));
            Interpolator interpolator = (Interpolator) c.get(a);
            if (interpolator == null) {
                throw new IllegalArgumentException("Missing interpolator for type : " + a);
            }
            this.d = interpolator;
            if (!a()) {
                throw new f("Invalid layout animation : " + data);
            }
            return;
        }
        throw new IllegalArgumentException("Missing interpolation type.");
    }

    @Nullable
    public final Animation b(View view, int x, int y, int width, int height) {
        if (!a()) {
            return null;
        }
        Animation animation = a(view, x, y, width, height);
        if (animation == null) {
            return animation;
        }
        animation.setDuration((long) (this.b * 1));
        animation.setStartOffset((long) (this.e * 1));
        animation.setInterpolator(this.d);
        return animation;
    }
}
