package com.facebook.react.flat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Animatable;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.drawee.c.q.b;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.d.e;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.views.a.a;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

final class k extends b implements ControllerListener, j {
    private final List<a> c = new LinkedList();
    @Nullable
    private n d;
    @Nullable
    private PorterDuffColorFilter e;
    private b f = b.g;
    private float g;
    private float h;
    private int i;
    private int j;
    private boolean k;
    private int l = 300;
    @Nullable
    private a m;

    k() {
    }

    public final void a(Context context, @Nullable al sources) {
        this.c.clear();
        if (sources != null && sources.size() != 0) {
            if (sources.size() == 1) {
                this.c.add(new a(context, sources.getMap(0).getString(ReactVideoViewManager.PROP_SRC_URI)));
                return;
            }
            for (int idx = 0; idx < sources.size(); idx++) {
                am source = sources.getMap(idx);
                this.c.add(new a(context, source.getString(ReactVideoViewManager.PROP_SRC_URI), source.getDouble("width"), source.getDouble("height")));
            }
        }
    }

    public final void a(int tintColor) {
        if (tintColor == 0) {
            this.e = null;
        } else {
            this.e = new PorterDuffColorFilter(tintColor, Mode.SRC_ATOP);
        }
    }

    public final void a(b scaleType) {
        this.f = scaleType;
    }

    public final b b() {
        return this.f;
    }

    public final void a(float borderWidth) {
        this.g = borderWidth;
    }

    public final float c() {
        return this.g;
    }

    public final void b(float borderRadius) {
        this.h = borderRadius;
    }

    public final float d() {
        return this.h;
    }

    public final void c(int borderColor) {
        this.i = borderColor;
    }

    public final int e() {
        return this.i;
    }

    public final void d(int fadeDuration) {
        this.l = fadeDuration;
    }

    public final void a(boolean enabled) {
        this.k = enabled;
    }

    public final void b(int reactTag) {
        this.j = reactTag;
    }

    public final void c(Canvas canvas) {
        if (this.d != null) {
            this.d.b().a().draw(canvas);
        }
    }

    public final void a(a callback) {
        this.m = callback;
        if (this.d == null) {
            throw new RuntimeException("No DraweeRequestHelper - width: " + (j() - h()) + " - height: " + (k() - i()) + " - number of sources: " + this.c.size());
        }
        Object obj;
        com.facebook.drawee.d.a hierarchy = this.d.b();
        e roundingParams = hierarchy.e();
        if (this.i != 0 || this.h >= 0.5f) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            if (roundingParams == null) {
                roundingParams = new e();
            }
            roundingParams.a(this.i, this.g);
            roundingParams.a(this.h);
            hierarchy.a(roundingParams);
        } else if (roundingParams != null) {
            hierarchy.a(null);
        }
        hierarchy.a(this.f);
        hierarchy.a(this.e);
        hierarchy.a(this.l);
        hierarchy.a().setBounds(Math.round(h()), Math.round(i()), Math.round(j()), Math.round(k()));
        this.d.a(callback);
    }

    public final void a() {
        if (this.d != null) {
            this.d.a();
        }
    }

    public final void onSubmit(String id, Object callerContext) {
        if (this.m != null && this.j != 0) {
            this.m.a(this.j, 4);
        }
    }

    public final void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
        if (this.m != null && this.j != 0) {
            this.m.a(this.j, 2);
            this.m.a(this.j, 3);
        }
    }

    public final void onIntermediateImageSet(String id, @Nullable Object imageInfo) {
    }

    public final void onIntermediateImageFailed(String id, Throwable throwable) {
    }

    public final void onFailure(String id, Throwable throwable) {
        if (this.m != null && this.j != 0) {
            this.m.a(this.j, 1);
            this.m.a(this.j, 3);
        }
    }

    public final void onRelease(String id) {
    }
}
