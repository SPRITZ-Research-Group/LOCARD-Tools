package com.skype.ink;

import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.n;

public class PathUtils {
    static float[] a(al value) {
        if (value == null) {
            return null;
        }
        float[] result = new float[value.size()];
        int length = value.size() > result.length ? result.length : value.size();
        for (int i = 0; i < length; i++) {
            result[i] = (float) value.getDouble(i);
        }
        value.size();
        return result;
    }

    static boolean a(Paint paint, float strokeWidth, float[] strokeColor, float scale) {
        if (strokeWidth == 0.0f || strokeColor == null || strokeColor.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.STROKE);
        paint.setStrokeCap(Cap.ROUND);
        paint.setStrokeJoin(Join.ROUND);
        paint.setStrokeWidth(strokeWidth * scale);
        paint.setARGB((int) (strokeColor.length > 3 ? (strokeColor[3] * 1.0f) * 255.0f : 255.0f), (int) (strokeColor[0] * 255.0f), (int) (strokeColor[1] * 255.0f), (int) (255.0f * strokeColor[2]));
        return true;
    }

    static boolean a(Paint paint, float[] fillColor) {
        if (fillColor == null || fillColor.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.FILL);
        paint.setARGB((int) (fillColor.length > 3 ? (fillColor[3] * 1.0f) * 255.0f : 255.0f), (int) (fillColor[0] * 255.0f), (int) (fillColor[1] * 255.0f), (int) (255.0f * fillColor[2]));
        return true;
    }

    static Path a(float[] data, float scale) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < data.length) {
            int i2 = i + 1;
            int type = (int) data[i];
            switch (type) {
                case 0:
                    i = i2 + 1;
                    i2 = i + 1;
                    path.moveTo(data[i2] * scale, data[i] * scale);
                    i = i2;
                    break;
                case 1:
                    path.close();
                    i = i2;
                    break;
                case 2:
                    i = i2 + 1;
                    i2 = i + 1;
                    path.lineTo(data[i2] * scale, data[i] * scale);
                    i = i2;
                    break;
                case 3:
                    i = i2 + 1;
                    i2 = i + 1;
                    i = i2 + 1;
                    i2 = i + 1;
                    i = i2 + 1;
                    i2 = i + 1;
                    path.cubicTo(data[i2] * scale, data[i] * scale, data[i2] * scale, data[i] * scale, data[i2] * scale, data[i] * scale);
                    i = i2;
                    break;
                case 4:
                    i = i2 + 1;
                    float x = data[i2] * scale;
                    i2 = i + 1;
                    float y = data[i] * scale;
                    i = i2 + 1;
                    float r = data[i2] * scale;
                    i2 = i + 1;
                    float start = (float) Math.toDegrees((double) data[i]);
                    i = i2 + 1;
                    float end = (float) Math.toDegrees((double) data[i2]);
                    i2 = i + 1;
                    if ((data[i] == 0.0f ? 1 : null) == null) {
                        end = 360.0f - end;
                    }
                    path.addArc(new RectF(x - r, y - r, x + r, y + r), start, start - end);
                    i = i2;
                    break;
                default:
                    throw new n("Unrecognized drawing instruction " + type);
            }
        }
        return path;
    }
}
