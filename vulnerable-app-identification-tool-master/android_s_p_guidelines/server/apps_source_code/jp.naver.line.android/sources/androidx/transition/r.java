package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.util.Property;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class r {
    static PropertyValuesHolder a(Property<?, PointF> property, Path path) {
        if (VERSION.SDK_INT >= 21) {
            return PropertyValuesHolder.ofObject(property, null, path);
        }
        return PropertyValuesHolder.ofFloat(new q(property, path), new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
    }
}
