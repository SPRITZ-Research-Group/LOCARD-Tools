package com.airbnb.lottie.c.b;

import android.graphics.PointF;
import android.support.annotation.FloatRange;
import com.airbnb.lottie.c.a;
import java.util.ArrayList;
import java.util.List;

public final class l {
    private final List<a> a = new ArrayList();
    private PointF b;
    private boolean c;

    public l(PointF initialPoint, boolean closed, List<a> curves) {
        this.b = initialPoint;
        this.c = closed;
        this.a.addAll(curves);
    }

    public final PointF a() {
        return this.b;
    }

    public final boolean b() {
        return this.c;
    }

    public final List<a> c() {
        return this.a;
    }

    public final void a(l shapeData1, l shapeData2, @FloatRange(from = 0.0d, to = 1.0d) float percentage) {
        if (this.b == null) {
            this.b = new PointF();
        }
        boolean z = shapeData1.c || shapeData2.c;
        this.c = z;
        if (this.a.isEmpty() || this.a.size() == shapeData1.a.size() || this.a.size() == shapeData2.a.size()) {
            int i;
            if (this.a.isEmpty()) {
                for (i = shapeData1.a.size() - 1; i >= 0; i--) {
                    this.a.add(new a());
                }
            }
            PointF initialPoint1 = shapeData1.b;
            PointF initialPoint2 = shapeData2.b;
            float f = initialPoint1.x;
            f += (initialPoint2.x - f) * percentage;
            float f2 = initialPoint1.y;
            f2 += (initialPoint2.y - f2) * percentage;
            if (this.b == null) {
                this.b = new PointF();
            }
            this.b.set(f, f2);
            for (i = this.a.size() - 1; i >= 0; i--) {
                a curve1 = (a) shapeData1.a.get(i);
                a curve2 = (a) shapeData2.a.get(i);
                PointF cp11 = curve1.a();
                PointF cp21 = curve1.b();
                PointF vertex1 = curve1.c();
                PointF cp12 = curve2.a();
                PointF cp22 = curve2.b();
                PointF vertex2 = curve2.c();
                a aVar = (a) this.a.get(i);
                f2 = cp11.x;
                f2 += (cp12.x - f2) * percentage;
                float f3 = cp11.y;
                aVar.a(f2, f3 + ((cp12.y - f3) * percentage));
                aVar = (a) this.a.get(i);
                f2 = cp21.x;
                f2 += (cp22.x - f2) * percentage;
                f3 = cp21.y;
                aVar.b(f2, f3 + ((cp22.y - f3) * percentage));
                aVar = (a) this.a.get(i);
                f2 = vertex1.x;
                f2 += (vertex2.x - f2) * percentage;
                f3 = vertex1.y;
                aVar.c(f2, f3 + ((vertex2.y - f3) * percentage));
            }
            return;
        }
        throw new IllegalStateException("Curves must have the same number of control points. This: " + this.a.size() + "\tShape 1: " + shapeData1.a.size() + "\tShape 2: " + shapeData2.a.size());
    }

    public final String toString() {
        return "ShapeData{numCurves=" + this.a.size() + "closed=" + this.c + '}';
    }
}
