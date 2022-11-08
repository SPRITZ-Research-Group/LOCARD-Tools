package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

final class al implements TypeEvaluator<Matrix> {
    final float[] a = new float[9];
    final float[] b = new float[9];
    final Matrix c = new Matrix();

    al() {
    }

    public final /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        Matrix matrix = (Matrix) obj2;
        ((Matrix) obj).getValues(this.a);
        matrix.getValues(this.b);
        for (int i = 0; i < 9; i++) {
            this.b[i] = this.a[i] + ((this.b[i] - this.a[i]) * f);
        }
        this.c.setValues(this.b);
        return this.c;
    }
}
