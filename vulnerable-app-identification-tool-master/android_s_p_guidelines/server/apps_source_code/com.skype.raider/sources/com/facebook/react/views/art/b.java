package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class b extends e {
    @Nullable
    protected Path a;
    @Nullable
    private float[] d;
    @Nullable
    private float[] f;
    @Nullable
    private float[] g;
    private float h = 1.0f;
    private int i = 1;
    private int j = 1;

    @ReactProp(name = "d")
    public void setShapePath(@Nullable al shapePath) {
        float[] pathData = f.a(shapePath);
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < pathData.length) {
            int i2 = i + 1;
            i = (int) pathData[i];
            int i3;
            switch (i) {
                case 0:
                    i3 = i2 + 1;
                    i = i3 + 1;
                    path.moveTo(this.c * pathData[i2], pathData[i3] * this.c);
                    break;
                case 1:
                    path.close();
                    i = i2;
                    break;
                case 2:
                    i3 = i2 + 1;
                    i = i3 + 1;
                    path.lineTo(this.c * pathData[i2], pathData[i3] * this.c);
                    break;
                case 3:
                    i3 = i2 + 1;
                    int i4 = i3 + 1;
                    int i5 = i4 + 1;
                    int i6 = i5 + 1;
                    int i7 = i6 + 1;
                    int i8 = i7 + 1;
                    path.cubicTo(pathData[i2] * this.c, pathData[i3] * this.c, pathData[i4] * this.c, pathData[i5] * this.c, pathData[i6] * this.c, pathData[i7] * this.c);
                    i = i8;
                    break;
                case 4:
                    float f;
                    i = i2 + 1;
                    float f2 = pathData[i2] * this.c;
                    i2 = i + 1;
                    float f3 = pathData[i] * this.c;
                    i = i2 + 1;
                    float f4 = pathData[i2] * this.c;
                    i2 = i + 1;
                    float toDegrees = (float) Math.toDegrees((double) pathData[i]);
                    i3 = i2 + 1;
                    float toDegrees2 = (float) Math.toDegrees((double) pathData[i2]);
                    i = i3 + 1;
                    Object obj = pathData[i3] == 1.0f ? 1 : null;
                    float f5 = toDegrees2 - toDegrees;
                    if (Math.abs(f5) > 360.0f) {
                        f5 = 360.0f;
                    } else {
                        f5 %= 360.0f;
                        if (f5 < 0.0f) {
                            f5 += 360.0f;
                        }
                    }
                    if (obj != null || f5 >= 360.0f) {
                        f = f5;
                        f5 = toDegrees;
                    } else {
                        f = 360.0f - f5;
                        f5 = toDegrees2;
                    }
                    path.arcTo(new RectF(f2 - f4, f3 - f4, f2 + f4, f3 + f4), f5, f);
                    break;
                default:
                    throw new n("Unrecognized drawing instruction " + i);
            }
        }
        this.a = path;
        i();
    }

    @ReactProp(name = "stroke")
    public void setStroke(@Nullable al strokeColors) {
        this.d = f.a(strokeColors);
        i();
    }

    @ReactProp(name = "strokeDash")
    public void setStrokeDash(@Nullable al strokeDash) {
        this.g = f.a(strokeDash);
        i();
    }

    @ReactProp(name = "fill")
    public void setFill(@Nullable al fillColors) {
        this.f = f.a(fillColors);
        i();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float strokeWidth) {
        this.h = strokeWidth;
        i();
    }

    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(int strokeCap) {
        this.i = strokeCap;
        i();
    }

    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(int strokeJoin) {
        this.j = strokeJoin;
        i();
    }

    public void a(Canvas canvas, Paint paint, float opacity) {
        opacity *= this.b;
        if (opacity > 0.01f) {
            a(canvas);
            if (this.a == null) {
                throw new n("Shapes should have a valid path (d) prop");
            }
            if (b(paint, opacity)) {
                canvas.drawPath(this.a, paint);
            }
            if (a(paint, opacity)) {
                canvas.drawPath(this.a, paint);
            }
            canvas.restore();
        }
        v();
    }

    protected final boolean a(Paint paint, float opacity) {
        if (this.h == 0.0f || this.d == null || this.d.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.STROKE);
        switch (this.i) {
            case 0:
                paint.setStrokeCap(Cap.BUTT);
                break;
            case 1:
                paint.setStrokeCap(Cap.ROUND);
                break;
            case 2:
                paint.setStrokeCap(Cap.SQUARE);
                break;
            default:
                throw new n("strokeCap " + this.i + " unrecognized");
        }
        switch (this.j) {
            case 0:
                paint.setStrokeJoin(Join.MITER);
                break;
            case 1:
                paint.setStrokeJoin(Join.ROUND);
                break;
            case 2:
                paint.setStrokeJoin(Join.BEVEL);
                break;
            default:
                throw new n("strokeJoin " + this.j + " unrecognized");
        }
        paint.setStrokeWidth(this.h * this.c);
        paint.setARGB((int) (this.d.length > 3 ? (this.d[3] * opacity) * 255.0f : opacity * 255.0f), (int) (this.d[0] * 255.0f), (int) (this.d[1] * 255.0f), (int) (this.d[2] * 255.0f));
        if (this.g != null && this.g.length > 0) {
            paint.setPathEffect(new DashPathEffect(this.g, 0.0f));
        }
        return true;
    }

    protected final boolean b(Paint paint, float opacity) {
        if (this.f == null || this.f.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.FILL);
        int colorType = (int) this.f[0];
        switch (colorType) {
            case 0:
                paint.setARGB((int) (this.f.length > 4 ? (this.f[4] * opacity) * 255.0f : 255.0f * opacity), (int) (this.f[1] * 255.0f), (int) (this.f[2] * 255.0f), (int) (this.f[3] * 255.0f));
                break;
            case 1:
                if (this.f.length >= 5) {
                    float gradientStartX = this.f[1] * this.c;
                    float gradientStartY = this.f[2] * this.c;
                    float gradientEndX = this.f[3] * this.c;
                    float gradientEndY = this.f[4] * this.c;
                    int stops = (this.f.length - 5) / 5;
                    int[] colors = null;
                    float[] positions = null;
                    if (stops > 0) {
                        colors = new int[stops];
                        positions = new float[stops];
                        for (int i = 0; i < stops; i++) {
                            positions[i] = this.f[((stops * 4) + 5) + i];
                            colors[i] = Color.argb((int) (255.0f * this.f[((i * 4) + 5) + 3]), (int) (255.0f * this.f[((i * 4) + 5) + 0]), (int) (255.0f * this.f[((i * 4) + 5) + 1]), (int) (255.0f * this.f[((i * 4) + 5) + 2]));
                        }
                    }
                    paint.setShader(new LinearGradient(gradientStartX, gradientStartY, gradientEndX, gradientEndY, colors, positions, TileMode.CLAMP));
                    break;
                }
                FLog.w("React", "[ARTShapeShadowNode setupFillPaint] expects 5 elements, received " + this.f.length);
                return false;
            default:
                FLog.w("React", "ART: Color type " + colorType + " not supported!");
                break;
        }
        return true;
    }
}
