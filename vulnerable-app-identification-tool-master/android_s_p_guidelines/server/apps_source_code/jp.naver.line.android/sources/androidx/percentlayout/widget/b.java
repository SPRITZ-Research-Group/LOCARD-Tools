package androidx.percentlayout.widget;

import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@Deprecated
public final class b {
    public float a = -1.0f;
    public float b = -1.0f;
    public float c = -1.0f;
    public float d = -1.0f;
    public float e = -1.0f;
    public float f = -1.0f;
    public float g = -1.0f;
    public float h = -1.0f;
    public float i;
    final d j = new d();

    public final void a(LayoutParams layoutParams, int i, int i2) {
        this.j.width = layoutParams.width;
        this.j.height = layoutParams.height;
        Object obj = null;
        Object obj2 = ((this.j.b || this.j.width == 0) && this.a < BitmapDescriptorFactory.HUE_RED) ? 1 : null;
        if ((this.j.a || this.j.height == 0) && this.b < BitmapDescriptorFactory.HUE_RED) {
            obj = 1;
        }
        if (this.a >= BitmapDescriptorFactory.HUE_RED) {
            layoutParams.width = Math.round(((float) i) * this.a);
        }
        if (this.b >= BitmapDescriptorFactory.HUE_RED) {
            layoutParams.height = Math.round(((float) i2) * this.b);
        }
        if (this.i >= BitmapDescriptorFactory.HUE_RED) {
            if (obj2 != null) {
                layoutParams.width = Math.round(((float) layoutParams.height) * this.i);
                this.j.b = true;
            }
            if (obj != null) {
                layoutParams.height = Math.round(((float) layoutParams.width) / this.i);
                this.j.a = true;
            }
        }
    }

    public final String toString() {
        return String.format("PercentLayoutInformation width: %f height %f, margins (%f, %f,  %f, %f, %f, %f)", new Object[]{Float.valueOf(this.a), Float.valueOf(this.b), Float.valueOf(this.c), Float.valueOf(this.d), Float.valueOf(this.e), Float.valueOf(this.f), Float.valueOf(this.g), Float.valueOf(this.h)});
    }

    public final void a(LayoutParams layoutParams) {
        if (!this.j.b) {
            layoutParams.width = this.j.width;
        }
        if (!this.j.a) {
            layoutParams.height = this.j.height;
        }
        this.j.b = false;
        this.j.a = false;
    }
}
