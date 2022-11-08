package android.support.b.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.content.res.b;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import com.microsoft.applications.telemetry.LogConfiguration;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({a.LIBRARY_GROUP})
public final class g implements Interpolator {
    private float[] a;
    private float[] b;

    public g(Context context, AttributeSet attrs, XmlPullParser parser) {
        this(context.getResources(), context.getTheme(), attrs, parser);
    }

    private g(Resources res, Theme theme, AttributeSet attrs, XmlPullParser parser) {
        TypedArray a = b.a(res, theme, attrs, a.l);
        if (b.a(parser, "pathData")) {
            String b = b.b(a, parser, "pathData", 4);
            Path a2 = android.support.v4.graphics.b.a(b);
            if (a2 == null) {
                throw new InflateException("The path is null, which is created from " + b);
            }
            a(a2);
        } else if (!b.a(parser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        } else if (b.a(parser, "controlY1")) {
            float a3 = b.a(a, parser, "controlX1", 0, 0.0f);
            float a4 = b.a(a, parser, "controlY1", 1, 0.0f);
            boolean a5 = b.a(parser, "controlX2");
            Path path;
            if (a5 != b.a(parser, "controlY2")) {
                throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
            } else if (a5) {
                float a6 = b.a(a, parser, "controlX2", 2, 0.0f);
                float a7 = b.a(a, parser, "controlY2", 3, 0.0f);
                path = new Path();
                path.moveTo(0.0f, 0.0f);
                path.cubicTo(a3, a4, a6, a7, 1.0f, 1.0f);
                a(path);
            } else {
                path = new Path();
                path.moveTo(0.0f, 0.0f);
                path.quadTo(a3, a4, 1.0f, 1.0f);
                a(path);
            }
        } else {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        a.recycle();
    }

    private void a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float pathLength = pathMeasure.getLength();
        int numPoints = Math.min(LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS, ((int) (pathLength / 0.002f)) + 1);
        if (numPoints <= 0) {
            throw new IllegalArgumentException("The Path has a invalid length " + pathLength);
        }
        int i;
        this.a = new float[numPoints];
        this.b = new float[numPoints];
        float[] position = new float[2];
        for (i = 0; i < numPoints; i++) {
            pathMeasure.getPosTan((((float) i) * pathLength) / ((float) (numPoints - 1)), position, null);
            this.a[i] = position[0];
            this.b[i] = position[1];
        }
        if (((double) Math.abs(this.a[0])) > 1.0E-5d || ((double) Math.abs(this.b[0])) > 1.0E-5d || ((double) Math.abs(this.a[numPoints - 1] - 1.0f)) > 1.0E-5d || ((double) Math.abs(this.b[numPoints - 1] - 1.0f)) > 1.0E-5d) {
            throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1) start: " + this.a[0] + "," + this.b[0] + " end:" + this.a[numPoints - 1] + "," + this.b[numPoints - 1]);
        }
        float prevX = 0.0f;
        i = 0;
        int componentIndex = 0;
        while (i < numPoints) {
            int componentIndex2 = componentIndex + 1;
            float x = this.a[componentIndex];
            if (x < prevX) {
                throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + x);
            }
            this.a[i] = x;
            prevX = x;
            i++;
            componentIndex = componentIndex2;
        }
        if (pathMeasure.nextContour()) {
            throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
        }
    }

    public final float getInterpolation(float t) {
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        int startIndex = 0;
        int endIndex = this.a.length - 1;
        while (endIndex - startIndex > 1) {
            int midIndex = (startIndex + endIndex) / 2;
            if (t < this.a[midIndex]) {
                endIndex = midIndex;
            } else {
                startIndex = midIndex;
            }
        }
        float xRange = this.a[endIndex] - this.a[startIndex];
        if (xRange == 0.0f) {
            return this.b[startIndex];
        }
        float fraction = (t - this.a[startIndex]) / xRange;
        float startY = this.b[startIndex];
        return ((this.b[endIndex] - startY) * fraction) + startY;
    }
}
