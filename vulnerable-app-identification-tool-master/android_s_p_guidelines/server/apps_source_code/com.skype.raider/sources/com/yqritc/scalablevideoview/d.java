package com.yqritc.scalablevideoview;

import android.graphics.Matrix;

public final class d {
    private e a;
    private e b;

    public d(e viewSize, e videoSize) {
        this.a = viewSize;
        this.b = videoSize;
    }

    public final Matrix a(c scalableType) {
        switch (scalableType) {
            case NONE:
                return a(((float) this.b.a()) / ((float) this.a.a()), ((float) this.b.b()) / ((float) this.a.b()), a.LEFT_TOP);
            case FIT_XY:
                return a(1.0f, 1.0f, a.LEFT_TOP);
            case FIT_CENTER:
                return a(a.CENTER);
            case FIT_START:
                return a(a.LEFT_TOP);
            case FIT_END:
                return a(a.RIGHT_BOTTOM);
            case LEFT_TOP:
                return b(a.LEFT_TOP);
            case LEFT_CENTER:
                return b(a.LEFT_CENTER);
            case LEFT_BOTTOM:
                return b(a.LEFT_BOTTOM);
            case CENTER_TOP:
                return b(a.CENTER_TOP);
            case CENTER:
                return b(a.CENTER);
            case CENTER_BOTTOM:
                return b(a.CENTER_BOTTOM);
            case RIGHT_TOP:
                return b(a.RIGHT_TOP);
            case RIGHT_CENTER:
                return b(a.RIGHT_CENTER);
            case RIGHT_BOTTOM:
                return b(a.RIGHT_BOTTOM);
            case LEFT_TOP_CROP:
                return c(a.LEFT_TOP);
            case LEFT_CENTER_CROP:
                return c(a.LEFT_CENTER);
            case LEFT_BOTTOM_CROP:
                return c(a.LEFT_BOTTOM);
            case CENTER_TOP_CROP:
                return c(a.CENTER_TOP);
            case CENTER_CROP:
                return c(a.CENTER);
            case CENTER_BOTTOM_CROP:
                return c(a.CENTER_BOTTOM);
            case RIGHT_TOP_CROP:
                return c(a.RIGHT_TOP);
            case RIGHT_CENTER_CROP:
                return c(a.RIGHT_CENTER);
            case RIGHT_BOTTOM_CROP:
                return c(a.RIGHT_BOTTOM);
            case START_INSIDE:
                if (this.b.b() > this.a.a() || this.b.b() > this.a.b()) {
                    return a(a.LEFT_TOP);
                }
                return b(a.LEFT_TOP);
            case CENTER_INSIDE:
                if (this.b.b() > this.a.a() || this.b.b() > this.a.b()) {
                    return a(a.CENTER);
                }
                return b(a.CENTER);
            case END_INSIDE:
                if (this.b.b() > this.a.a() || this.b.b() > this.a.b()) {
                    return a(a.RIGHT_BOTTOM);
                }
                return b(a.RIGHT_BOTTOM);
            default:
                return null;
        }
    }

    private static Matrix a(float sx, float sy, float px, float py) {
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy, px, py);
        return matrix;
    }

    private Matrix a(float sx, float sy, a pivotPoint) {
        switch (pivotPoint) {
            case LEFT_TOP:
                return a(sx, sy, 0.0f, 0.0f);
            case LEFT_CENTER:
                return a(sx, sy, 0.0f, ((float) this.a.b()) / 2.0f);
            case LEFT_BOTTOM:
                return a(sx, sy, 0.0f, (float) this.a.b());
            case CENTER_TOP:
                return a(sx, sy, ((float) this.a.a()) / 2.0f, 0.0f);
            case CENTER:
                return a(sx, sy, ((float) this.a.a()) / 2.0f, ((float) this.a.b()) / 2.0f);
            case CENTER_BOTTOM:
                return a(sx, sy, ((float) this.a.a()) / 2.0f, (float) this.a.b());
            case RIGHT_TOP:
                return a(sx, sy, (float) this.a.a(), 0.0f);
            case RIGHT_CENTER:
                return a(sx, sy, (float) this.a.a(), ((float) this.a.b()) / 2.0f);
            case RIGHT_BOTTOM:
                return a(sx, sy, (float) this.a.a(), (float) this.a.b());
            default:
                throw new IllegalArgumentException("Illegal PivotPoint");
        }
    }

    private Matrix a(a pivotPoint) {
        float sx = ((float) this.a.a()) / ((float) this.b.a());
        float sy = ((float) this.a.b()) / ((float) this.b.b());
        float minScale = Math.min(sx, sy);
        return a(minScale / sx, minScale / sy, pivotPoint);
    }

    private Matrix b(a pivotPoint) {
        return a(((float) this.b.a()) / ((float) this.a.a()), ((float) this.b.b()) / ((float) this.a.b()), pivotPoint);
    }

    private Matrix c(a pivotPoint) {
        float sx = ((float) this.a.a()) / ((float) this.b.a());
        float sy = ((float) this.a.b()) / ((float) this.b.b());
        float maxScale = Math.max(sx, sy);
        return a(maxScale / sx, maxScale / sy, pivotPoint);
    }
}
