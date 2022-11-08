package com.facebook.drawee.d;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.c.c;
import com.facebook.drawee.c.g;
import com.facebook.drawee.c.j;
import com.facebook.drawee.c.k;
import com.facebook.drawee.c.l;
import com.facebook.drawee.c.m;
import com.facebook.drawee.c.o;
import com.facebook.drawee.c.p;
import com.facebook.drawee.c.q.b;
import com.facebook.drawee.d.e.a;
import javax.annotation.Nullable;

public final class f {
    private static final Drawable a = new ColorDrawable(0);

    @Nullable
    static Drawable a(@Nullable Drawable drawable, @Nullable b scaleType) {
        return a(drawable, scaleType, null);
    }

    @Nullable
    static Drawable a(@Nullable Drawable drawable, @Nullable b scaleType, @Nullable PointF focusPoint) {
        com.facebook.imagepipeline.l.b.a();
        if (drawable == null || scaleType == null) {
            com.facebook.imagepipeline.l.b.a();
            return drawable;
        }
        Drawable scaleTypeDrawable = new p(drawable, scaleType);
        if (focusPoint != null) {
            scaleTypeDrawable.a(focusPoint);
        }
        com.facebook.imagepipeline.l.b.a();
        return scaleTypeDrawable;
    }

    static p a(c parent, b scaleType) {
        Object child = a(parent.a(a), scaleType, null);
        parent.a(child);
        h.a(child, (Object) "Parent has no child drawable!");
        return (p) child;
    }

    static void a(c parent, @Nullable e roundingParams) {
        Drawable child = parent.a();
        if (roundingParams == null || roundingParams.c() != a.OVERLAY_COLOR) {
            if (child instanceof m) {
                parent.a(((m) child).b(a));
                a.setCallback(null);
            }
        } else if (child instanceof m) {
            j roundedCornersDrawable = (m) child;
            a(roundedCornersDrawable, roundingParams);
            roundedCornersDrawable.a(roundingParams.d());
        } else {
            parent.a(a(parent.a(a), roundingParams));
        }
    }

    static void a(c parent, @Nullable e roundingParams, Resources resources) {
        parent = a(parent);
        Drawable child = parent.a();
        if (roundingParams == null || roundingParams.c() != a.BITMAP_ONLY) {
            if (child instanceof j) {
                j jVar = (j) child;
                jVar.a(false);
                jVar.h_();
                jVar.a(0, 0.0f);
                jVar.a(0.0f);
                jVar.b(false);
            }
        } else if (child instanceof j) {
            a((j) child, roundingParams);
        } else if (child != null) {
            parent.a(a);
            parent.a(b(child, roundingParams, resources));
        }
    }

    static Drawable a(@Nullable Drawable drawable, @Nullable e roundingParams) {
        try {
            com.facebook.imagepipeline.l.b.a();
            if (drawable == null || roundingParams == null || roundingParams.c() != a.OVERLAY_COLOR) {
                com.facebook.imagepipeline.l.b.a();
                return drawable;
            }
            j roundedCornersDrawable = new m(drawable);
            a(roundedCornersDrawable, roundingParams);
            roundedCornersDrawable.a(roundingParams.d());
            com.facebook.imagepipeline.l.b.a();
            return roundedCornersDrawable;
        } catch (Throwable th) {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    static Drawable a(@Nullable Drawable drawable, @Nullable e roundingParams, Resources resources) {
        try {
            com.facebook.imagepipeline.l.b.a();
            if (drawable == null || roundingParams == null || roundingParams.c() != a.BITMAP_ONLY) {
                com.facebook.imagepipeline.l.b.a();
            } else if (drawable instanceof g) {
                c parent = a((g) drawable);
                parent.a(b(parent.a(a), roundingParams, resources));
                com.facebook.imagepipeline.l.b.a();
            } else {
                drawable = b(drawable, roundingParams, resources);
                com.facebook.imagepipeline.l.b.a();
            }
            return drawable;
        } catch (Throwable th) {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    private static Drawable b(Drawable drawable, e roundingParams, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            j roundedBitmapDrawable = new k(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            a(roundedBitmapDrawable, roundingParams);
            return roundedBitmapDrawable;
        } else if (drawable instanceof NinePatchDrawable) {
            j roundedNinePatchDrawable = new o((NinePatchDrawable) drawable);
            a(roundedNinePatchDrawable, roundingParams);
            return roundedNinePatchDrawable;
        } else if (!(drawable instanceof ColorDrawable) || VERSION.SDK_INT < 11) {
            FLog.w("WrappingUtils", "Don't know how to round that drawable: %s", drawable);
            return drawable;
        } else {
            j roundedColorDrawable = l.a((ColorDrawable) drawable);
            a(roundedColorDrawable, roundingParams);
            return roundedColorDrawable;
        }
    }

    private static void a(j rounded, e roundingParams) {
        rounded.a(roundingParams.a());
        rounded.a(roundingParams.b());
        rounded.a(roundingParams.g(), roundingParams.f());
        rounded.a(roundingParams.h());
        rounded.b(roundingParams.i());
    }

    private static c a(c parent) {
        while (true) {
            Drawable child = parent.a();
            if (child == parent || !(child instanceof c)) {
                return parent;
            }
            parent = (c) child;
        }
        return parent;
    }
}
