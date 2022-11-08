package androidx.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

final class q<T> extends Property<T, Float> {
    private final Property<T, PointF> a;
    private final PathMeasure b;
    private final float c;
    private final float[] d = new float[2];
    private final PointF e = new PointF();
    private float f;

    public final /* synthetic */ void set(Object obj, Object obj2) {
        Float f = (Float) obj2;
        this.f = f.floatValue();
        this.b.getPosTan(this.c * f.floatValue(), this.d, null);
        this.e.x = this.d[0];
        this.e.y = this.d[1];
        this.a.set(obj, this.e);
    }

    q(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.a = property;
        this.b = new PathMeasure(path, false);
        this.c = this.b.getLength();
    }

    public final /* synthetic */ Object get(Object obj) {
        return Float.valueOf(this.f);
    }
}
