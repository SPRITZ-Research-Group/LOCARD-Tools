package androidx.transition;

import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;

final class f {
    final float a;
    final float b;
    final float c;
    final float d;
    final float e;
    final float f;
    final float g;
    final float h;

    f(View view) {
        this.a = view.getTranslationX();
        this.b = view.getTranslationY();
        this.c = hs.q(view);
        this.d = view.getScaleX();
        this.e = view.getScaleY();
        this.f = view.getRotationX();
        this.g = view.getRotationY();
        this.h = view.getRotation();
    }

    public final void a(View view) {
        ChangeTransform.a(view, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (fVar.a == this.a && fVar.b == this.b && fVar.c == this.c && fVar.d == this.d && fVar.e == this.e && fVar.f == this.f && fVar.g == this.g && fVar.h == this.h) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int floatToIntBits = (((((((((((((this.a != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.a) : 0) * 31) + (this.b != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.b) : 0)) * 31) + (this.c != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.c) : 0)) * 31) + (this.d != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.d) : 0)) * 31) + (this.e != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.e) : 0)) * 31) + (this.f != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.f) : 0)) * 31) + (this.g != BitmapDescriptorFactory.HUE_RED ? Float.floatToIntBits(this.g) : 0)) * 31;
        if (this.h != BitmapDescriptorFactory.HUE_RED) {
            i = Float.floatToIntBits(this.h);
        }
        return floatToIntBits + i;
    }
}
