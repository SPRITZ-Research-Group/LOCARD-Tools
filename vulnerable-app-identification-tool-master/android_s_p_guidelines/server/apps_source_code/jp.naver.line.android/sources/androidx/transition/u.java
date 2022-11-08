package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

final class u implements TypeEvaluator<Rect> {
    private Rect a;

    public final /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        Rect rect = (Rect) obj;
        Rect rect2 = (Rect) obj2;
        int i = rect.left + ((int) (((float) (rect2.left - rect.left)) * f));
        int i2 = rect.top + ((int) (((float) (rect2.top - rect.top)) * f));
        int i3 = rect.right + ((int) (((float) (rect2.right - rect.right)) * f));
        int i4 = rect.bottom + ((int) (((float) (rect2.bottom - rect.bottom)) * f));
        if (this.a == null) {
            return new Rect(i, i2, i3, i4);
        }
        this.a.set(i, i2, i3, i4);
        return this.a;
    }

    u() {
    }

    u(Rect rect) {
        this.a = rect;
    }
}
