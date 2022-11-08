package com.microsoft.react.clippedview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.ViewGroup;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.b;
import javax.annotation.Nullable;

public class ClippedView extends ViewGroup {
    protected float[] a;
    protected float b;
    @Nullable
    protected Path c;

    public ClippedView(Context context) {
        super(context);
        setClipChildren(true);
    }

    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
    }

    public void setShapePath(@Nullable al shapePath) {
        float[] fArr;
        if (shapePath != null) {
            float[] fArr2 = new float[shapePath.size()];
            int length = shapePath.size() > fArr2.length ? fArr2.length : shapePath.size();
            for (int i = 0; i < length; i++) {
                fArr2[i] = (float) shapePath.getDouble(i);
            }
            shapePath.size();
            fArr = fArr2;
        } else {
            fArr = null;
        }
        this.a = fArr;
        this.c = null;
        this.b = 0.0f;
    }

    protected void dispatchDraw(Canvas canvas) {
        Path path;
        int save = canvas.save();
        if (this.a.length == 0) {
            path = null;
        } else {
            float f = b.b().density;
            if (this.b != f) {
                this.b = f;
                float[] fArr = this.a;
                float f2 = b.b().density;
                Path path2 = new Path();
                path2.moveTo(0.0f, 0.0f);
                int i = 0;
                while (i < fArr.length) {
                    int i2 = i + 1;
                    i = (int) fArr[i];
                    int i3;
                    switch (i) {
                        case 0:
                            i3 = i2 + 1;
                            i = i3 + 1;
                            path2.moveTo(fArr[i2] * f2, fArr[i3] * f2);
                            break;
                        case 1:
                            path2.close();
                            i = i2;
                            break;
                        case 2:
                            i3 = i2 + 1;
                            i = i3 + 1;
                            path2.lineTo(fArr[i2] * f2, fArr[i3] * f2);
                            break;
                        case 3:
                            i3 = i2 + 1;
                            int i4 = i3 + 1;
                            int i5 = i4 + 1;
                            int i6 = i5 + 1;
                            int i7 = i6 + 1;
                            int i8 = i7 + 1;
                            path2.cubicTo(fArr[i2] * f2, fArr[i3] * f2, fArr[i4] * f2, fArr[i5] * f2, fArr[i6] * f2, fArr[i7] * f2);
                            i = i8;
                            break;
                        case 4:
                            i = i2 + 1;
                            float f3 = fArr[i2] * f2;
                            i2 = i + 1;
                            float f4 = fArr[i] * f2;
                            i = i2 + 1;
                            float f5 = fArr[i2] * f2;
                            i2 = i + 1;
                            float toDegrees = (float) Math.toDegrees((double) fArr[i]);
                            i3 = i2 + 1;
                            float toDegrees2 = (float) Math.toDegrees((double) fArr[i2]);
                            i = i3 + 1;
                            if ((fArr[i3] == 0.0f ? 1 : null) == null) {
                                toDegrees2 = 360.0f - toDegrees2;
                            }
                            path2.addArc(new RectF(f3 - f5, f4 - f5, f3 + f5, f4 + f5), toDegrees, toDegrees - toDegrees2);
                            break;
                        default:
                            throw new n("Unrecognized drawing instruction " + i);
                    }
                }
                this.c = path2;
            }
            path = this.c;
        }
        if (path != null) {
            canvas.clipPath(path);
        }
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }
}
