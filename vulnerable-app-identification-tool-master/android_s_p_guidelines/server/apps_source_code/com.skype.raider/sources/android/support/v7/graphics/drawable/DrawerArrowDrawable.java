package android.support.v7.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class DrawerArrowDrawable extends Drawable {
    private static final float b = ((float) Math.toRadians(45.0d));
    private final Paint a;
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private final Path h;
    private final int i;
    private boolean j;
    private float k;
    private float l;
    private int m;

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrowDirection {
    }

    public final void draw(Canvas canvas) {
        boolean flipToPointRight;
        float f;
        Rect bounds = getBounds();
        switch (this.m) {
            case 0:
                flipToPointRight = false;
                break;
            case 1:
                flipToPointRight = true;
                break;
            case 3:
                if (android.support.v4.graphics.drawable.a.h(this) != 0) {
                    flipToPointRight = false;
                    break;
                } else {
                    flipToPointRight = true;
                    break;
                }
            default:
                if (android.support.v4.graphics.drawable.a.h(this) != 1) {
                    flipToPointRight = false;
                    break;
                } else {
                    flipToPointRight = true;
                    break;
                }
        }
        float arrowHeadBarLength = (float) Math.sqrt((double) ((this.c * this.c) * 2.0f));
        float f2 = this.d;
        arrowHeadBarLength = f2 + (this.k * (arrowHeadBarLength - f2));
        f2 = this.d;
        float arrowShaftLength = f2 + ((this.e - f2) * this.k);
        float arrowShaftCut = (float) Math.round(0.0f + ((this.l - 0.0f) * this.k));
        float rotation = 0.0f + ((b - 0.0f) * this.k);
        if (flipToPointRight) {
            f = 0.0f;
        } else {
            f = -180.0f;
        }
        if (flipToPointRight) {
            f2 = 180.0f;
        } else {
            f2 = 0.0f;
        }
        float canvasRotate = f + ((f2 - f) * this.k);
        float arrowWidth = (float) Math.round(((double) arrowHeadBarLength) * Math.cos((double) rotation));
        float arrowHeight = (float) Math.round(((double) arrowHeadBarLength) * Math.sin((double) rotation));
        this.h.rewind();
        f2 = this.f + this.a.getStrokeWidth();
        float topBottomBarOffset = f2 + (((-this.l) - f2) * this.k);
        float arrowEdge = (-arrowShaftLength) / 2.0f;
        this.h.moveTo(arrowEdge + arrowShaftCut, 0.0f);
        this.h.rLineTo(arrowShaftLength - (2.0f * arrowShaftCut), 0.0f);
        this.h.moveTo(arrowEdge, topBottomBarOffset);
        this.h.rLineTo(arrowWidth, arrowHeight);
        this.h.moveTo(arrowEdge, -topBottomBarOffset);
        this.h.rLineTo(arrowWidth, -arrowHeight);
        this.h.close();
        canvas.save();
        float barThickness = this.a.getStrokeWidth();
        canvas.translate((float) bounds.centerX(), ((float) ((((int) ((((float) bounds.height()) - (3.0f * barThickness)) - (this.f * 2.0f))) / 4) * 2)) + ((1.5f * barThickness) + this.f));
        if (this.g) {
            canvas.rotate(((float) ((this.j ^ flipToPointRight) != 0 ? -1 : 1)) * canvasRotate);
        } else if (flipToPointRight) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.h, this.a);
        canvas.restore();
    }

    public final void setAlpha(int alpha) {
        if (alpha != this.a.getAlpha()) {
            this.a.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final int getIntrinsicHeight() {
        return this.i;
    }

    public final int getIntrinsicWidth() {
        return this.i;
    }

    public final int getOpacity() {
        return -3;
    }
}
