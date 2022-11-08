package androidx.transition;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.View;

final class e {
    private final Matrix a = new Matrix();
    private final View b;
    private final float[] c;
    private float d;
    private float e;

    e(View view, float[] fArr) {
        this.b = view;
        this.c = (float[]) fArr.clone();
        this.d = this.c[2];
        this.e = this.c[5];
        b();
    }

    final void a(float[] fArr) {
        System.arraycopy(fArr, 0, this.c, 0, fArr.length);
        b();
    }

    final void a(PointF pointF) {
        this.d = pointF.x;
        this.e = pointF.y;
        b();
    }

    private void b() {
        this.c[2] = this.d;
        this.c[5] = this.e;
        this.a.setValues(this.c);
        ba.c(this.b, this.a);
    }

    final Matrix a() {
        return this.a;
    }
}
