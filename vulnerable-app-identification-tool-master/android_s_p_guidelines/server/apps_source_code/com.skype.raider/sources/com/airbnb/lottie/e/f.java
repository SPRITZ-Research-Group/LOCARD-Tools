package com.airbnb.lottie.e;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.a.a.r;
import com.airbnb.lottie.d;

public final class f {
    private static final PathMeasure a = new PathMeasure();
    private static final Path b = new Path();
    private static final Path c = new Path();
    private static final float[] d = new float[4];
    private static final float e = ((float) Math.sqrt(2.0d));
    private static float f = -1.0f;

    public static Path a(PointF startPoint, PointF endPoint, PointF cp1, PointF cp2) {
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        if (cp1 == null || cp2 == null || (cp1.length() == 0.0f && cp2.length() == 0.0f)) {
            path.lineTo(endPoint.x, endPoint.y);
        } else {
            path.cubicTo(startPoint.x + cp1.x, startPoint.y + cp1.y, endPoint.x + cp2.x, endPoint.y + cp2.y, endPoint.x, endPoint.y);
        }
        return path;
    }

    public static float a(Matrix matrix) {
        d[0] = 0.0f;
        d[1] = 0.0f;
        d[2] = e;
        d[3] = e;
        matrix.mapPoints(d);
        return ((float) Math.hypot((double) (d[2] - d[0]), (double) (d[3] - d[1]))) / 2.0f;
    }

    public static void a(Path path, @Nullable r trimPath) {
        if (trimPath != null) {
            a(path, ((Float) trimPath.d().e()).floatValue() / 100.0f, ((Float) trimPath.e().e()).floatValue() / 100.0f, ((Float) trimPath.f().e()).floatValue() / 360.0f);
        }
    }

    public static void a(Path path, float startValue, float endValue, float offsetValue) {
        d.a("applyTrimPathIfNeeded");
        a.setPath(path, false);
        float length = a.getLength();
        if (startValue == 1.0f && endValue == 0.0f) {
            d.b("applyTrimPathIfNeeded");
        } else if (length < 1.0f || ((double) Math.abs((endValue - startValue) - 1.0f)) < 0.01d) {
            d.b("applyTrimPathIfNeeded");
        } else {
            float start = length * startValue;
            float end = length * endValue;
            float offset = offsetValue * length;
            float newStart = Math.min(start, end) + offset;
            float newEnd = Math.max(start, end) + offset;
            if (newStart >= length && newEnd >= length) {
                newStart = (float) e.a(newStart, length);
                newEnd = (float) e.a(newEnd, length);
            }
            if (newStart < 0.0f) {
                newStart = (float) e.a(newStart, length);
            }
            if (newEnd < 0.0f) {
                newEnd = (float) e.a(newEnd, length);
            }
            if (newStart == newEnd) {
                path.reset();
                d.b("applyTrimPathIfNeeded");
                return;
            }
            if (newStart >= newEnd) {
                newStart -= length;
            }
            b.reset();
            a.getSegment(newStart, newEnd, b, true);
            if (newEnd > length) {
                c.reset();
                a.getSegment(0.0f, newEnd % length, c, true);
                b.addPath(c);
            } else if (newStart < 0.0f) {
                c.reset();
                a.getSegment(length + newStart, length, c, true);
                b.addPath(c);
            }
            path.set(b);
            d.b("applyTrimPathIfNeeded");
        }
    }

    public static boolean a(int major, int minor, int patch) {
        if (major < 4) {
            return false;
        }
        if (major > 4) {
            return true;
        }
        if (minor < 4) {
            return false;
        }
        if (minor > 4) {
            return true;
        }
        if (patch >= 0) {
            return true;
        }
        return false;
    }

    public static int a(float a, float b, float c, float d) {
        int result = 17;
        if (a != 0.0f) {
            result = (int) (527.0f * a);
        }
        if (b != 0.0f) {
            result = (int) (((float) (result * 31)) * b);
        }
        if (c != 0.0f) {
            result = (int) (((float) (result * 31)) * c);
        }
        if (d != 0.0f) {
            return (int) (((float) (result * 31)) * d);
        }
        return result;
    }

    public static float a() {
        if (f == -1.0f) {
            f = Resources.getSystem().getDisplayMetrics().density;
        }
        return f;
    }
}
