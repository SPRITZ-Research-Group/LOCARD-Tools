package android.support.v4.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

final class e implements Interpolator {
    private final float[] a;
    private final float[] b;

    private e(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float pathLength = pathMeasure.getLength();
        int numPoints = ((int) (pathLength / 0.002f)) + 1;
        this.a = new float[numPoints];
        this.b = new float[numPoints];
        float[] position = new float[2];
        for (int i = 0; i < numPoints; i++) {
            pathMeasure.getPosTan((((float) i) * pathLength) / ((float) (numPoints - 1)), position, null);
            this.a[i] = position[0];
            this.b[i] = position[1];
        }
    }

    e(float controlX1, float controlY1, float controlX2, float controlY2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(controlX1, controlY1, controlX2, controlY2, 1.0f, 1.0f);
        this(path);
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
