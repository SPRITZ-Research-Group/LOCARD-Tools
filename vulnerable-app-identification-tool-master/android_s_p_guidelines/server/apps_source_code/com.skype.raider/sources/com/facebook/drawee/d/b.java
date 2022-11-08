package com.facebook.drawee.d;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.h;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

public final class b {
    public static final com.facebook.drawee.c.q.b a = com.facebook.drawee.c.q.b.f;
    public static final com.facebook.drawee.c.q.b b = com.facebook.drawee.c.q.b.g;
    private Resources c;
    private int d = 300;
    private float e = 0.0f;
    private Drawable f = null;
    @Nullable
    private com.facebook.drawee.c.q.b g = a;
    private Drawable h = null;
    private com.facebook.drawee.c.q.b i = a;
    private Drawable j = null;
    private com.facebook.drawee.c.q.b k = a;
    private Drawable l = null;
    private com.facebook.drawee.c.q.b m = a;
    private com.facebook.drawee.c.q.b n = b;
    private Matrix o = null;
    private PointF p = null;
    private ColorFilter q = null;
    private Drawable r = null;
    private List<Drawable> s = null;
    private Drawable t = null;
    private e u = null;

    public b(Resources resources) {
        this.c = resources;
    }

    public static b a(Resources resources) {
        return new b(resources);
    }

    public final Resources a() {
        return this.c;
    }

    public final b a(int fadeDuration) {
        this.d = fadeDuration;
        return this;
    }

    public final int b() {
        return this.d;
    }

    public final b a(float desiredAspectRatio) {
        this.e = desiredAspectRatio;
        return this;
    }

    public final float c() {
        return this.e;
    }

    public final b a(@Nullable Drawable placeholderDrawable) {
        this.f = placeholderDrawable;
        return this;
    }

    @Nullable
    public final Drawable d() {
        return this.f;
    }

    public final b a(@Nullable com.facebook.drawee.c.q.b placeholderImageScaleType) {
        this.g = placeholderImageScaleType;
        return this;
    }

    @Nullable
    public final com.facebook.drawee.c.q.b e() {
        return this.g;
    }

    public final b b(@Nullable Drawable retryDrawable) {
        this.h = retryDrawable;
        return this;
    }

    @Nullable
    public final Drawable f() {
        return this.h;
    }

    public final b b(@Nullable com.facebook.drawee.c.q.b retryImageScaleType) {
        this.i = retryImageScaleType;
        return this;
    }

    @Nullable
    public final com.facebook.drawee.c.q.b g() {
        return this.i;
    }

    public final b c(@Nullable Drawable failureDrawable) {
        this.j = failureDrawable;
        return this;
    }

    @Nullable
    public final Drawable h() {
        return this.j;
    }

    public final b c(@Nullable com.facebook.drawee.c.q.b failureImageScaleType) {
        this.k = failureImageScaleType;
        return this;
    }

    @Nullable
    public final com.facebook.drawee.c.q.b i() {
        return this.k;
    }

    public final b d(@Nullable Drawable progressBarDrawable) {
        this.l = progressBarDrawable;
        return this;
    }

    @Nullable
    public final Drawable j() {
        return this.l;
    }

    public final b d(@Nullable com.facebook.drawee.c.q.b progressBarImageScaleType) {
        this.m = progressBarImageScaleType;
        return this;
    }

    @Nullable
    public final com.facebook.drawee.c.q.b k() {
        return this.m;
    }

    public final b e(@Nullable com.facebook.drawee.c.q.b actualImageScaleType) {
        this.n = actualImageScaleType;
        this.o = null;
        return this;
    }

    @Nullable
    public final com.facebook.drawee.c.q.b l() {
        return this.n;
    }

    @Nullable
    public final PointF m() {
        return this.p;
    }

    @Nullable
    public final ColorFilter n() {
        return this.q;
    }

    public final b e(@Nullable Drawable background) {
        this.r = background;
        return this;
    }

    @Nullable
    public final Drawable o() {
        return this.r;
    }

    public final b f(@Nullable Drawable overlay) {
        if (overlay == null) {
            this.s = null;
        } else {
            this.s = Arrays.asList(new Drawable[]{overlay});
        }
        return this;
    }

    @Nullable
    public final List<Drawable> p() {
        return this.s;
    }

    public final b g(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.t = null;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, drawable);
            this.t = stateListDrawable;
        }
        return this;
    }

    @Nullable
    public final Drawable q() {
        return this.t;
    }

    public final b a(@Nullable e roundingParams) {
        this.u = roundingParams;
        return this;
    }

    @Nullable
    public final e r() {
        return this.u;
    }

    public final a s() {
        if (this.s != null) {
            for (Object a : this.s) {
                h.a(a);
            }
        }
        return new a(this);
    }
}
