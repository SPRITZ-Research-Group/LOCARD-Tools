package com.facebook.drawee.d;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import com.facebook.drawee.a.a;
import com.facebook.drawee.c.b;
import com.facebook.drawee.c.q;
import com.facebook.infer.annotation.ReturnsOwnership;
import javax.annotation.Nullable;

public final class c {
    public static b a(b builder, Context context, @Nullable AttributeSet attrs) {
        int progressBarAutoRotateInterval = 0;
        int roundedCornerRadius = 0;
        boolean roundTopLeft = true;
        boolean roundTopRight = true;
        boolean roundBottomLeft = true;
        boolean roundBottomRight = true;
        boolean roundTopStart = true;
        boolean roundTopEnd = true;
        boolean roundBottomStart = true;
        boolean roundBottomEnd = true;
        if (attrs != null) {
            TypedArray gdhAttrs = context.obtainStyledAttributes(attrs, a.GenericDraweeHierarchy);
            try {
                int indexCount = gdhAttrs.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int attr = gdhAttrs.getIndex(i);
                    if (attr == a.GenericDraweeHierarchy_actualImageScaleType) {
                        builder.e(a(gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_placeholderImage) {
                        builder.a(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_pressedStateOverlayImage) {
                        builder.g(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_progressBarImage) {
                        builder.d(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_fadeDuration) {
                        builder.a(gdhAttrs.getInt(attr, 0));
                    } else if (attr == a.GenericDraweeHierarchy_viewAspectRatio) {
                        builder.a(gdhAttrs.getFloat(attr, 0.0f));
                    } else if (attr == a.GenericDraweeHierarchy_placeholderImageScaleType) {
                        builder.a(a(gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_retryImage) {
                        builder.b(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_retryImageScaleType) {
                        builder.b(a(gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_failureImage) {
                        builder.c(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_failureImageScaleType) {
                        builder.c(a(gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_progressBarImageScaleType) {
                        builder.d(a(gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_progressBarAutoRotateInterval) {
                        progressBarAutoRotateInterval = gdhAttrs.getInteger(attr, progressBarAutoRotateInterval);
                    } else if (attr == a.GenericDraweeHierarchy_backgroundImage) {
                        builder.e(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_overlayImage) {
                        builder.f(a(context, gdhAttrs, attr));
                    } else if (attr == a.GenericDraweeHierarchy_roundAsCircle) {
                        a(builder).a(gdhAttrs.getBoolean(attr, false));
                    } else if (attr == a.GenericDraweeHierarchy_roundedCornerRadius) {
                        roundedCornerRadius = gdhAttrs.getDimensionPixelSize(attr, roundedCornerRadius);
                    } else if (attr == a.GenericDraweeHierarchy_roundTopLeft) {
                        roundTopLeft = gdhAttrs.getBoolean(attr, roundTopLeft);
                    } else if (attr == a.GenericDraweeHierarchy_roundTopRight) {
                        roundTopRight = gdhAttrs.getBoolean(attr, roundTopRight);
                    } else if (attr == a.GenericDraweeHierarchy_roundBottomLeft) {
                        roundBottomLeft = gdhAttrs.getBoolean(attr, roundBottomLeft);
                    } else if (attr == a.GenericDraweeHierarchy_roundBottomRight) {
                        roundBottomRight = gdhAttrs.getBoolean(attr, roundBottomRight);
                    } else if (attr == a.GenericDraweeHierarchy_roundTopStart) {
                        roundTopStart = gdhAttrs.getBoolean(attr, roundTopStart);
                    } else if (attr == a.GenericDraweeHierarchy_roundTopEnd) {
                        roundTopEnd = gdhAttrs.getBoolean(attr, roundTopEnd);
                    } else if (attr == a.GenericDraweeHierarchy_roundBottomStart) {
                        roundBottomStart = gdhAttrs.getBoolean(attr, roundBottomStart);
                    } else if (attr == a.GenericDraweeHierarchy_roundBottomEnd) {
                        roundBottomEnd = gdhAttrs.getBoolean(attr, roundBottomEnd);
                    } else if (attr == a.GenericDraweeHierarchy_roundWithOverlayColor) {
                        a(builder).a(gdhAttrs.getColor(attr, 0));
                    } else if (attr == a.GenericDraweeHierarchy_roundingBorderWidth) {
                        a(builder).b((float) gdhAttrs.getDimensionPixelSize(attr, 0));
                    } else if (attr == a.GenericDraweeHierarchy_roundingBorderColor) {
                        a(builder).b(gdhAttrs.getColor(attr, 0));
                    } else if (attr == a.GenericDraweeHierarchy_roundingBorderPadding) {
                        a(builder).c((float) gdhAttrs.getDimensionPixelSize(attr, 0));
                    }
                }
                gdhAttrs.recycle();
                if (VERSION.SDK_INT < 17 || context.getResources().getConfiguration().getLayoutDirection() != 1) {
                    roundTopLeft = roundTopLeft && roundTopStart;
                    roundTopRight = roundTopRight && roundTopEnd;
                    roundBottomRight = roundBottomRight && roundBottomEnd;
                    roundBottomLeft = roundBottomLeft && roundBottomStart;
                } else {
                    roundTopLeft = roundTopLeft && roundTopEnd;
                    roundTopRight = roundTopRight && roundTopStart;
                    roundBottomRight = roundBottomRight && roundBottomStart;
                    roundBottomLeft = roundBottomLeft && roundBottomEnd;
                }
            } catch (Throwable th) {
                gdhAttrs.recycle();
                if (VERSION.SDK_INT >= 17) {
                    context.getResources().getConfiguration().getLayoutDirection();
                }
            }
        }
        if (builder.j() != null && progressBarAutoRotateInterval > 0) {
            builder.d(new b(builder.j(), progressBarAutoRotateInterval));
        }
        if (roundedCornerRadius > 0) {
            a(builder).a(roundTopLeft ? (float) roundedCornerRadius : 0.0f, roundTopRight ? (float) roundedCornerRadius : 0.0f, roundBottomRight ? (float) roundedCornerRadius : 0.0f, roundBottomLeft ? (float) roundedCornerRadius : 0.0f);
        }
        return builder;
    }

    @ReturnsOwnership
    private static e a(b builder) {
        if (builder.r() == null) {
            builder.a(new e());
        }
        return builder.r();
    }

    @Nullable
    private static Drawable a(Context context, TypedArray gdhAttrs, int attrId) {
        int resourceId = gdhAttrs.getResourceId(attrId, 0);
        return resourceId == 0 ? null : context.getResources().getDrawable(resourceId);
    }

    @Nullable
    private static q.b a(TypedArray gdhAttrs, int attrId) {
        switch (gdhAttrs.getInt(attrId, -2)) {
            case -1:
                return null;
            case 0:
                return q.b.a;
            case 1:
                return q.b.b;
            case 2:
                return q.b.c;
            case 3:
                return q.b.d;
            case 4:
                return q.b.e;
            case 5:
                return q.b.f;
            case 6:
                return q.b.g;
            case 7:
                return q.b.h;
            case 8:
                return q.b.i;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }
}
